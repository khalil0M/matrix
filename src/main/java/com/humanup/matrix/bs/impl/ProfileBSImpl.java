package com.humanup.matrix.bs.impl;

import com.humanup.matrix.aop.dto.ProfileException;
import com.humanup.matrix.bs.ProfileBS;
import com.humanup.matrix.bs.impl.sender.RabbitMQProfileSender;
import com.humanup.matrix.dao.ProfileDAO;
import com.humanup.matrix.dao.entities.Profile;
import com.humanup.matrix.vo.ProfileVO;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ProfileBSImpl implements ProfileBS {
  private static final Logger LOGGER = LoggerFactory.getLogger(ProfileBSImpl.class);

  @Autowired private ProfileDAO profileDAO;
  @Autowired RabbitMQProfileSender rabbitMQProfileSender;

  @Override
  @Transactional(
      transactionManager = "transactionManagerWrite",
      rollbackFor = ProfileException.class)
  public boolean createProfile(ProfileVO profileVO) throws ProfileException {
    if (null == profileVO) throw new ProfileException();
    rabbitMQProfileSender.send(profileVO);
    return true;
  }

  @Override
  public ProfileVO findProfileByTitle(String profileTitle) {
    Optional<Profile> profileFinded =
        Optional.ofNullable(profileDAO.findByProfileTitle(profileTitle));
    if (profileFinded.isPresent()) {
      return ProfileVO.builder()
          .profileTitle(profileFinded.get().getProfileTitle())
          .profileDescription(profileFinded.get().getProfileDescription())
          .countPerson(
              null != profileFinded.get().getPersonList()
                  ? profileFinded.get().getPersonList().size()
                  : 0)
          .build();
    }
    return null;
  }

  @Override
  public List<ProfileVO> findListProfile() {
    return profileDAO.findAll().stream()
        .map(
            profileFinded ->
                ProfileVO.builder()
                    .profileTitle(profileFinded.getProfileTitle())
                    .profileDescription(profileFinded.getProfileDescription())
                    .countPerson(
                        null != profileFinded.getPersonList()
                            ? profileFinded.getPersonList().size()
                            : 0)
                    .build())
        .collect(Collectors.toList());
  }

  @Override
  public List<ProfileVO> findListProfilesByTitle(String title) {
    Optional<List<Profile>> listProfileFinded =
        Optional.ofNullable(profileDAO.findListProfilesByTitle(title));
    if (listProfileFinded.isPresent()) {
      return listProfileFinded.get().stream()
          .map(
              profileFinded ->
                  ProfileVO.builder()
                      .profileTitle(profileFinded.getProfileTitle())
                      .profileDescription(profileFinded.getProfileDescription())
                      .countPerson(
                          null != profileFinded.getPersonList()
                              ? profileFinded.getPersonList().size()
                              : 0)
                      .build())
          .collect(Collectors.toList());
    }
    return null;
  }
}

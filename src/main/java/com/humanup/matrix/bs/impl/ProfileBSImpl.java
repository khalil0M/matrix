package com.humanup.matrix.bs.impl;

import com.humanup.matrix.bs.ProfileBS;
import com.humanup.matrix.dao.ProfileDAO;
import com.humanup.matrix.dao.entities.Profile;
import com.humanup.matrix.vo.PersonVO;
import com.humanup.matrix.vo.ProfileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProfileBSImpl implements ProfileBS {

    @Autowired
    private ProfileDAO profileDAO;

    @Override
    @Transactional(transactionManager="transactionManagerWrite")
    public boolean createProfile(ProfileVO profileVO) {
        Profile profileToSave = Profile.builder()
                .profileTitle(profileVO.getProfileTitle())
                .profileDescription(profileVO.getProfileDescription())
                .build();
        return  profileDAO.save(profileToSave)!=null;
    }

    @Override
    public ProfileVO findProfileByTitle(String profileTitle) {
        Optional<Profile> profileFinded = Optional.ofNullable(profileDAO.findByProfileTitle(profileTitle));
        if(profileFinded.isPresent()) {
            return  ProfileVO.builder()
                    .profileTitle(profileFinded.get().getProfileTitle())
                    .profileDescription(profileFinded.get().getProfileDescription())
                    .countPerson(null!=profileFinded.get().getPersonList()?profileFinded.get().getPersonList().size():0)
                    .build();
        }
        return null;
    }

    @Override
    public List<ProfileVO> findListProfile() {
        return profileDAO.findAll()
                .stream()
                .map(profileFinded ->  ProfileVO.builder()
                        .profileTitle(profileFinded.getProfileTitle())
                        .profileDescription(profileFinded.getProfileDescription())
                        .countPerson(null!=profileFinded.getPersonList()?profileFinded.getPersonList().size():0)
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public List<ProfileVO> findListProfilesByTitle(String title) {
        Optional<List<Profile>> listProfileFinded = Optional.ofNullable(profileDAO.findListProfilesByTitle(title));
        if(listProfileFinded.isPresent()) {
            return listProfileFinded.get()
                    .stream()
                    .map(profileFinded ->  ProfileVO.builder()
                            .profileTitle(profileFinded.getProfileTitle())
                            .profileDescription(profileFinded.getProfileDescription())
                            .countPerson(null!=profileFinded.getPersonList()?profileFinded.getPersonList().size():0)
                            .build())
                     .collect(Collectors.toList());
        }
        return null;
    }

}

package com.humanup.matrix.bs.impl;

import com.humanup.matrix.bs.SkillBS;
import com.humanup.matrix.dao.SkillDAO;
import com.humanup.matrix.dao.TypeSkillsDAO;
import com.humanup.matrix.dao.entities.Skill;
import com.humanup.matrix.dao.entities.TypeSkills;
import com.humanup.matrix.vo.SkillVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class SkillBSImpl implements SkillBS {

    @Autowired
    private SkillDAO skillDAO;

    @Autowired
    private TypeSkillsDAO typeSkillsDAO;

    @Override
    @Transactional(transactionManager="transactionManagerWrite")
    public boolean createSkill(SkillVO skillVO) {
        TypeSkills typeSkills = typeSkillsDAO.findByTitleSkill(skillVO.getTypeSkills());

        Skill skillToSave =  Skill.builder()
                .libelle(skillVO.getLibelle())
                .description(skillVO.getDescription())
                .typeSkills(typeSkills)
                .build();
        return skillDAO.save(skillToSave) != null;
    }

    @Override
    public SkillVO findSkillByLibelle(String libelle) {
        Optional<Skill> skillFinded = Optional.ofNullable(skillDAO.findSkillByLibelle(libelle));
        if (skillFinded.isPresent()) {
            TypeSkills typeSkills = skillFinded.get().getTypeSkills();
            return  SkillVO.builder()
                    .libelle(skillFinded.get().getLibelle())
                    .description(skillFinded.get().getDescription())
                    .typeSkills(typeSkills.getTitleSkill())
                    .idTypeSkills(typeSkills.getTypeId())

                    .build();
        }
        return null;
    }

    @Override
    public List<SkillVO> findListSkillByTypeTitle(String titleSkill) {
		return skillDAO.findListSkillByTypeTitle(titleSkill)
				.stream()
				.map(skillFinded ->  SkillVO.builder()
						.libelle(skillFinded.getLibelle())
						.description(skillFinded.getDescription())
						.typeSkills(skillFinded.getTypeSkills().getTitleSkill())
						.build())
				.collect(Collectors.toList());
    }

    @Override
    public List<SkillVO> findListSkill() {
        return skillDAO.findAll()
                .stream()
                .map(skillFinded ->  SkillVO.builder()
                        .libelle(skillFinded.getLibelle())
                        .description(skillFinded.getDescription())
                        .typeSkills(skillFinded.getTypeSkills().getTitleSkill())
                        .build())
                .collect(Collectors.toList());
    }

}

package com.backend.service.impl;

import com.backend.constant.Constant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.backend.bean.PageBean;
import com.backend.domain.entity.DesktopPet;
import com.backend.domain.query.DesktopPetQuery;
import com.backend.mapper.DesktopPetMapper;
import com.backend.service.DesktopPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class DesktopPetServiceImpl implements DesktopPetService {
    @Autowired
    private DesktopPetMapper desktopPetMapper;

    @Override
    public PageBean<DesktopPet> getPage(DesktopPetQuery desktopPetQuery) {
        final Integer pageNum = desktopPetQuery.getPageNum();
        final Integer pageSize = desktopPetQuery.getPageSize();
        final Page<DesktopPet> page = PageHelper.startPage(pageNum, pageSize);
        desktopPetMapper.selectWithCondition(desktopPetQuery);
        final int total = desktopPetMapper.countByCondition(desktopPetQuery);
        return PageBean.page2pageBean(page, (long) total);
    }

    @Override
    public List<DesktopPet> getAll(DesktopPetQuery desktopPetQuery) {
        return desktopPetMapper.selectWithCondition(desktopPetQuery);
    }

    @Override
    public DesktopPet getById(Long id) {
        return desktopPetMapper.selectByPrimaryKey(id);
    }

    @Override
    public int insertOrUpdate(DesktopPet desktopPet) {
        return desktopPetMapper.insertOrUpdateSelective(desktopPet);
    }

    @Override
    public int deleteById(Long id) {
        return desktopPetMapper.deleteByPrimaryKey(id);
    }

    @Override
     @Transactional(rollbackFor = Exception.class)
    public int deleteByIds(String ids) {
        final String[] split = ids.split(",");
        return desktopPetMapper.deleteByPrimaryKeyIn(split);
    }

    @Override
    public int batchInsert(List<DesktopPet> desktopPetList) {
        return desktopPetMapper.batchInsertSelectiveUseDefaultForNull(desktopPetList);
    }

    @Override
    public void onNewTodo(Long userId) {
        DesktopPet pet = getDesktopPetByUserId(userId);
        if (pet == null) {
            return;
        }
        
        LocalDate today = LocalDate.now();
        
        if (pet.getLastEnergyResetDate() == null) {
            pet.setLastEnergyResetDate(LocalDateTime.now());
        } else if (!today.equals(pet.getLastEnergyResetDate().toLocalDate())) {
            pet.setEnergy(0);
            pet.setLastEnergyResetDate(LocalDateTime.now());
        }
        
        int newEnergy = pet.getEnergy() + Constant.DESKTOP_PET_ENERGY_INCREASE;
        pet.setEnergy(newEnergy > Constant.DESKTOP_PET_MAX_VALUE ? Constant.DESKTOP_PET_MAX_VALUE : newEnergy);
        
        desktopPetMapper.updateByPrimaryKeySelective(pet);
    }

    @Override
    public void onTodoCompleted(Long userId, boolean isCompletedOnTime) {
        DesktopPet pet = getDesktopPetByUserId(userId);
        if (pet == null) {
            return;
        }
        
        LocalDate today = LocalDate.now();
        
        if (pet.getLastEnergyResetDate() == null) {
            pet.setLastEnergyResetDate(LocalDateTime.now());
        } else if (!today.equals(pet.getLastEnergyResetDate().toLocalDate())) {
            pet.setEnergy(0);
            pet.setLastEnergyResetDate(LocalDateTime.now());
        }
        
        int newEnergy = pet.getEnergy() + Constant.DESKTOP_PET_ENERGY_INCREASE;
        pet.setEnergy(newEnergy > Constant.DESKTOP_PET_MAX_VALUE ? Constant.DESKTOP_PET_MAX_VALUE : newEnergy);
        
        int newExp = pet.getExp() + Constant.DESKTOP_PET_EXP_INCREASE;
        if (newExp >= Constant.DESKTOP_PET_EXP_LEVEL_UP) {
            pet.setLevel(pet.getLevel() + 1);
            pet.setExp(newExp - Constant.DESKTOP_PET_EXP_LEVEL_UP);
        } else {
            pet.setExp(newExp);
        }
        
        if (isCompletedOnTime) {
            int newMood = pet.getMood() + Constant.DESKTOP_PET_MOOD_ON_TIME_INCREASE;
            pet.setMood(newMood > Constant.DESKTOP_PET_MAX_VALUE ? Constant.DESKTOP_PET_MAX_VALUE : newMood);
        } else {
            int newMood = pet.getMood() - Constant.DESKTOP_PET_MOOD_OVERDUE_DECREASE;
            pet.setMood(newMood < 0 ? 0 : newMood);
        }
        
        desktopPetMapper.updateByPrimaryKeySelective(pet);
    }

    @Override
    public void updateIntimacyOnLogin(Long userId) {
        DesktopPet pet = getDesktopPetByUserId(userId);
        if (pet == null) {
            return;
        }
        
        LocalDate today = LocalDate.now();
        
        if (pet.getLastLoginDate() == null) {
            pet.setLastLoginDate(LocalDateTime.now());
            pet.setConsecutiveDays(Constant.DESKTOP_PET_CONSECUTIVE_DAYS_INIT);
            int newIntimacy = pet.getIntimacy() + Constant.DESKTOP_PET_INTIMACY_INCREASE;
            pet.setIntimacy(newIntimacy > Constant.DESKTOP_PET_MAX_VALUE ? Constant.DESKTOP_PET_MAX_VALUE : newIntimacy);
            desktopPetMapper.updateByPrimaryKeySelective(pet);
        } else if (!today.equals(pet.getLastLoginDate().toLocalDate())) {
            LocalDate yesterday = today.minusDays(1);
            
            if (yesterday.equals(pet.getLastLoginDate().toLocalDate())) {
                pet.setConsecutiveDays(pet.getConsecutiveDays() + 1);
            } else {
                pet.setConsecutiveDays(Constant.DESKTOP_PET_CONSECUTIVE_DAYS_INIT);
            }
            
            int newIntimacy = pet.getIntimacy() + Constant.DESKTOP_PET_INTIMACY_INCREASE;
            pet.setIntimacy(newIntimacy > Constant.DESKTOP_PET_MAX_VALUE ? Constant.DESKTOP_PET_MAX_VALUE : newIntimacy);
            
            pet.setLastLoginDate(LocalDateTime.now());
            desktopPetMapper.updateByPrimaryKeySelective(pet);
        }
    }

    @Override
    public void dailyReset() {
        DesktopPet energyPet = new DesktopPet();
        energyPet.setEnergy(0);
        energyPet.setLastEnergyResetDate(LocalDate.now());
        
        DesktopPet moodPet = new DesktopPet();
        moodPet.setMood(Constant.DESKTOP_PET_MOOD_DEFAULT);
        moodPet.setLastMoodResetDate(LocalDate.now());
        
        desktopPetMapper.updateBatchSelective(List.of(energyPet, moodPet));
    }

    private DesktopPet getDesktopPetByUserId(Long userId) {
        DesktopPetQuery query = new DesktopPetQuery();
        query.setUserId(userId);
        List<DesktopPet> pets = desktopPetMapper.selectWithCondition(query);
        
        if (pets == null || pets.isEmpty()) {
            return null;
        }
        
        return pets.get(0);
    }
}

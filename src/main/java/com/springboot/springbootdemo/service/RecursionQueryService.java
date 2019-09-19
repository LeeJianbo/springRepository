package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.dto.OrgDTO;
import com.springboot.springbootdemo.entity.Org;
import com.springboot.springbootdemo.repository.OrgRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 递归查询机构列表
 * date: 2019-09-19 16:59
 * author: Lee
 */
@Service
public class RecursionQueryService {
    @Autowired
    private OrgRepository orgRepository;
    public List<OrgDTO> listOrg() {
        List<OrgDTO> orgDTOS = new ArrayList<>();
        // 查询所有节点
        List<Org> orgs = orgRepository.findAll((root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList();
            predicateList.add(cb.equal(root.get("status"), "1"));
            Predicate[] ps = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(ps)).getRestriction();
        });
        List<Long> parentIds = orgs.stream().map(s -> s.getParentId()).collect(Collectors.toList());
        // 查询根节点
        List<Org> rootOrgs = orgRepository.findAll((root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList();
            predicateList.add(cb.equal(root.get("status"), "1"));
            predicateList.add(cb.equal(root.get("parentId"), Collections.min(parentIds)));
            Predicate[] ps = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(ps)).getRestriction();
        });
        // 获取根节点下的子节点
        rootOrgs.forEach(s -> {
            OrgDTO orgDTO = new OrgDTO();
            BeanUtils.copyProperties(s, orgDTO);
            orgDTO.setChild(getChildList(s));
            orgDTOS.add(orgDTO);
        });
        return orgDTOS;
    }

    /**
     * 查询机构下的子机构列表（递归）
     *
     * @param org
     * @return
     */
    public List<OrgDTO> getChildList(Org org) {
        // 获取org下的子机构，并转换成OrgDTO
        List<OrgDTO> orgChildList = orgRepository.findAll((root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList();
            predicateList.add(cb.equal(root.get("status"), "1"));
            predicateList.add(cb.equal(root.get("parentId"), org.getId()));
            Predicate[] ps = new Predicate[predicateList.size()];
            return query.where(predicateList.toArray(ps)).getRestriction();
        }).stream().map(s -> {
            OrgDTO orgDTO = new OrgDTO();
            BeanUtils.copyProperties(s, orgDTO);
            return orgDTO;
        }).collect(Collectors.toList());
        if (orgChildList.size() > 0) {
            // 递归查询orgChildList中所有机构的子机构
            orgChildList.forEach(s -> {
                Org org1 = new Org();
                BeanUtils.copyProperties(s, org1);
                List<OrgDTO> childList = getChildList(org1);
                s.setChild(childList);
            });
        }
        return orgChildList;
    }

}

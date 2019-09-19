package com.springboot.springbootdemo.repository;


import com.springboot.springbootdemo.entity.Org;

import java.util.List;

/**
 * description: ShareSwapRepository
 * date: 2019-09-05 13:28
 * author: Lee
 */
public interface OrgRepository extends BaseRepository<Org, Long> {


    /**
     * 根据id和状态查询所有的子机构
     *
     * @param id
     * @param status
     * @return
     */
    List<Org> findAllByParentIdAndStatus(Long id, String status);

    /**
     * 查询名称是否重复
     *
     * @param status
     * @param id
     * @return
     */
    List<Org> findAllByStatusAndIdNot(String status, Long id);

    /**
     * 查询所有机构名
     *
     * @return
     */
    List<Org> findAllByStatusAndName(String status, String name);

}

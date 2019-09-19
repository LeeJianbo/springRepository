package com.springboot.springbootdemo.service;

import com.springboot.springbootdemo.dto.UserDTO;
import com.springboot.springbootdemo.entity.User;
import com.springboot.springbootdemo.repository.UserReposiroty;
import javafx.scene.transform.Rotate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserReposiroty userReposiroty;

    /**
     * 添加用户
     *
     * @param userDTO
     */
    public void addUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user.setStatus("1");
        userReposiroty.save(user);
    }

    /**
     * 按name查询
     *
     * @param userDTO
     * @return
     */
    public UserDTO findUserByName(UserDTO userDTO) {
        User user = userReposiroty.findUserByName(userDTO.getName());
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    /**
     * 分页查询
     *
     * @param pageable
     * @return
     */
    public Page<UserDTO> pageList(Pageable pageable) {
        // 新建查询条件
        /** Specification参数说明
         * Root<User> root：
         * query
         * cb
         */
        Specification<User> specification = (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> list = new ArrayList<>();
            List<Order> orders = new ArrayList<>();
            // 添加排序规则
            orders.add(cb.desc(root.get("createdDate")));
            query.orderBy(orders);
            // 添加查询条件
            list.add(cb.equal(root.get("status"), "1"));
            Predicate[] ps = new Predicate[list.size()];
            // 条件查询
            return query.where(list.toArray(ps)).getRestriction();
        };
        // 创建排序规则，用于传入findAll函数中。与Specification中构造的排序查询效果一样
        Sort sort = new Sort(Sort.Direction.ASC);
        // 将查询出的User转换成UserDTO
        Page<UserDTO> page = userReposiroty.findAll(specification, pageable).map(d -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(d, userDTO);
            return userDTO;
        });
        return page;
    }
}

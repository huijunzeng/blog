package com.teeya.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.teeya.user.entity.form.ResourceSaveForm;
import com.teeya.user.entity.form.ResourceQueryForm;
import com.teeya.user.entity.form.UserUpdateForm;
import com.teeya.user.entity.pojo.ResourceEntity;

import java.util.List;
import java.util.Set;

public interface ResourceService extends IService<ResourceEntity> {

    /**
     * 新增资源
     * @param resourceSaveForm
     */
    boolean save(ResourceSaveForm resourceSaveForm);

    /**
     * 修改资源
     * @param id
     * @param resourceUpdateForm
     * @return
     */
    boolean update(String id, UserUpdateForm resourceUpdateForm);

    /**
     * 根据用户id获取相应的资源集合
     * @param id
     * @return
     */
    ResourceEntity get(String id);

    /**
     * 根据资源id集合获取相应的资源集合
     * @param resourceIds
     */
    List<ResourceEntity> queryListByResourceIds(Set<String> resourceIds);

    /**
     * 根据用户id获取相应的资源集合
     * @param userId
     * @return
     */
    List<ResourceEntity> queryListByUserId(String userId);

    /**
     * 根据用户名获取相应的资源集合
     * @param username
     * @return
     */
    List<ResourceEntity> queryListByUsername(String username);

    /**
     * 根据角色code获取相应的资源集合
     * @param roleId
     * @return
     */
    List<ResourceEntity> queryListByRoleId(String roleId);

    /**
     * 根据条件获取资源信息列表
     * @param resourceQueryForm
     * @return
     */
    IPage queryList(ResourceQueryForm resourceQueryForm);

    /**
     * 获取所有资源集合
     * @return
     */
    List<ResourceEntity> getAll();

    /**
     * 根据id删除资源
     * @param id
     * @return
     */
    boolean remove(String id);
}

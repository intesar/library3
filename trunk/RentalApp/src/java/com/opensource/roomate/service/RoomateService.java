/*
 *
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 *
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */

package com.opensource.roomate.service;

import com.opensource.roomate.entity.ContactUs;
import com.opensource.roomate.entity.ResultDto;
import com.opensource.roomate.entity.Post;

/**
 *
 * @author intesar shannan mohammed
 *  mdshannan@gmail.com
 */
public interface RoomateService {

    /**
     *
     * @param post
     * @param ip
     */
    void addPost(Post post, String ip);

    /**
     *
     * @param post
     * @param email
     * @param ip
     */
    void updatePost(Post post, String email, String ip);

    /**
     *
     * @param postId
     * @param email
     * @param ip
     */
    void deletePost(long postId, String email, String ip);

    /**
     *
     * @param postId
     * @param message
     * @param ip
     */
    void sendMessage(long postId, String message, String ip);

    /**
     *
     * @param email
     * @param keywords
     * @param ip
     */
    void subscribeUser(String email, String keywords, String ip);

    /**
     *
     * @param keywords
     * @param currentPage
     * @param pageSize
     * @return
     */
    ResultDto search(String keywords, int currentPage, int pageSize);

    /**
     *
     * @param id
     * @return
     */
    ResultDto searchById(Long id);

    /**
     *
     * @param email
     * @param id
     * @return
     */
    ResultDto searchByEmailAndId(String email, long id);

    /**
     * records a abuse report
     * email admin after a posted is listed couple of times as abuse
     * @param postId
     * @param reportType
     * @param reportIp
     */
    void reportAbuse(Long postId, String reportType, String reportIp);

    /**
     *
     */
    void reIndex();

    /**
     *
     * @param contactUs
     */
    void contactUs(ContactUs contactUs);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

    void addPost(Post post, String ip);

    void updatePost(Post post, String email, String ip);

    void deletePost(long postId, String email, String ip);

    void sendMessage(long postId, String message, String ip);

    void subscribeUser(String email, String keywords, String ip);

    ResultDto search(String keywords, int currentPage, int pageSize);

    ResultDto searchById(Long id);

    ResultDto searchByEmailAndId(String email, long id);

    /**
     * records a abuse report
     * email admin after a posted is listed couple of times as abuse
     * @param postId
     * @param reportType
     * @param reportIp
     */
    void reportAbuse(Long postId, String reportType, String reportIp );

    void reIndex();

    void contactUs(ContactUs contactUs);
}

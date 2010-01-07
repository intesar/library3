/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.opensource.roomate.service;

import com.opensource.roomate.entity.Post;
import java.util.List;

/**
 *
 * @author intesar shannan mohammed
 *  mdshannan@gmail.com
 */
public interface RoomateService {

    void post(Post post);

    List<Post> search(String keywords, int currentPage, int pageSize);

}

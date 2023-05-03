package com.carespoon.friendList.controller;

import com.carespoon.friendList.repository.FriendListRepositoryCustom;
import com.carespoon.friendList.dto.FriendListSaveDto;
import com.carespoon.friendList.service.FriendListService;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class FriendListController {

    private final  FriendListService friendListService;

    @Autowired
    @Qualifier("friendListRepositoryImpl")
    private final FriendListRepositoryCustom friendListRepositoryCustom;
    @RequestMapping(value = "/friendlist" , method = RequestMethod.POST, produces = "application/json")
    public void save(@RequestBody FriendListSaveDto friendListSaveDto){
        friendListService.save(friendListSaveDto);
    }

    @GetMapping("/friendsof")
    public List<Tuple> getFriend(@RequestParam String uuid, @RequestParam String role){
        List<Tuple> friendsOf;
        if(role.equals("senior")){
            friendsOf = friendListRepositoryCustom.findBySeniorId(uuid);
        }else{
            friendsOf = friendListRepositoryCustom.findByViewerId(uuid);
        }
        return friendsOf;
    }

    @DeleteMapping("/friendlist/remove")
    public void friendRemove(@RequestParam String seniorId, @RequestParam String viewerId){
        Long id = friendListRepositoryCustom.findIdByUUID(seniorId, viewerId);
        friendListService.deleteFriend(id);
    }
}
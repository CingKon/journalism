package com.journalism.service;

import com.journalism.common.ServerResponse;
import com.journalism.pojo.Anthology;

public interface IAnthologyService {

    ServerResponse save(Anthology anthology);

    ServerResponse list(Integer authorId, int pageNum, int pageSize);

    ServerResponse delete(Integer authorId,Integer anthologyId);
}

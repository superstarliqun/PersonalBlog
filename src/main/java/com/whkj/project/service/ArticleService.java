package com.whkj.project.service;

import com.whkj.project.entity.Article;
import com.whkj.project.utils.IDRequest;
import com.whkj.project.utils.RestResult;

public interface ArticleService {

    RestResult page(Integer num, Integer size, String keyWords);

    RestResult get(Integer id);

    RestResult saveAndEdit(Article article);

    RestResult remove(Integer id);
}

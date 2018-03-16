package com.jin.service.impl;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.jin.pojo.Question;
import com.jin.service.SearchService;
import com.jin.utils.MyUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by JINS on 2018/3/15.
 */
@Service
public class SearchServiceImpl implements SearchService{

    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImpl.class);

    @Autowired
    private SolrServer solrServer;




    @Override
    public List<Question> searchQuestion(String keyWord, int offset, int count, String hlPre, String hlpos) throws Exception{
        SolrQuery query = new  SolrQuery();
        if (StringUtils.isNotEmpty(keyWord)) {
            String str = "question_title:" + keyWord+"or question_content:"+keyWord;
            query.set("q", str);
        } else {
            query.setQuery("*:*");
        }

        query.setStart(offset);
        query.setRows(count);


        query.setHighlight(true);
        query.set("hl.fl",MyUtils.QUESTION_TITLE_FIELD+","+MyUtils.QUESTION_CONTENT_FIELD);
        query.setHighlightSimplePre(hlPre);
        query.setHighlightSimplePost(hlpos);

        QueryResponse response = solrServer.query(query);

        SolrDocumentList results = response.getResults();

        List<Question> questions = new ArrayList<>();
        Question q;
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        for (SolrDocument doc : results) {
            q = new Question();

            String id = (String) doc.get("id");
            q.setId(Integer.valueOf(id));

            List<String> list1 = highlighting.get(id).get(MyUtils.QUESTION_TITLE_FIELD);
            if (list1 != null)
                q.setTitle(list1.get(0));
            else {
                q.setTitle(doc.get(MyUtils.QUESTION_TITLE_FIELD).toString());
            }

            List<String> list2 = highlighting.get(id).get(MyUtils.QUESTION_CONTENT_FIELD);
            if (list2 != null)
                q.setContent(list2.get(0));
            else {
                q.setContent(doc.get(MyUtils.QUESTION_CONTENT_FIELD).toString());
            }

           questions.add(q);
        }


        return questions;
    }

    @Override
    public boolean addIndexQuestion(int id, String title, String content)throws Exception {
        SolrInputDocument doc = new SolrInputDocument();
        doc.setField("id",id);
        doc.setField(MyUtils.QUESTION_TITLE_FIELD,title);
        doc.setField(MyUtils.QUESTION_CONTENT_FIELD,content);

        UpdateResponse response = solrServer.add(doc,1000);
        solrServer.commit();

        return response!=null && response.getStatus() == 0;
    }
}

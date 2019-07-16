package com.example.demo;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.es.Goods;
import com.example.demo.es.GoodsRepository;
import com.example.demo.multidatasource.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecurityApplicationTests {
	
	@Autowired
	private UserService userService;
	@Autowired
	private GoodsRepository repository;
	@Test
	public void testMultiDataSource() throws Exception {
		com.example.demo.multidatasource.domain.User user = userService.findByName("cc2s11");
		System.out.println(user.toString());
	}
	@Test
	public void testElasticSearch() throws Exception {
		System.setProperty("es.set.netty.runtime.available.processors","false");
		repository.deleteAll();

        Goods goods = new Goods(1L, "Macbook苹果散热底座", "铝合金金属笔记本电脑通用支架Macbook苹果散热底座桌面托架");
        repository.save(goods);

        Goods goods2 = new Goods(2L, "Beats Solo3 Wireless", "Beats studio3 Wireless头戴式无线耳机蓝牙麦录音师苹果");
        repository.save(goods2);

        Goods goods3 = new Goods(3L, "Apple Watch Series3", "Apple Watch/苹果 Series 3 苹果智能手表3代 42mm GPS款");
        repository.save(goods3);



        Pageable pageable = PageRequest.of(0, 10);
        QueryBuilder queryBuilder =  QueryBuilders.boolQuery()
                .must(QueryBuilders.termQuery("name", "macbook"))
                .mustNot(QueryBuilders.termQuery("description", "Wireless"))
                .should(QueryBuilders.termQuery("id", 3L));
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(queryBuilder).build();
        Page<Goods> goodsPage = repository.search(searchQuery);
        List<Goods> content = goodsPage.getContent();

        System.out.println(content);
	}
	
}

package com.eshop.repo;

import com.eshop.model.goods;
import org.springframework.data.repository.CrudRepository;

public interface goodsRepository extends CrudRepository<goods, Integer> {
}

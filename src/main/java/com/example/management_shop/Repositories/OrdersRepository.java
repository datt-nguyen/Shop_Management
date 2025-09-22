package com.example.management_shop.Repositories;
import com.example.management_shop.Entity.Orders;
import com.example.management_shop.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long>{
    List<Orders> findByUser_Id(Long userId);
}

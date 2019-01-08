package com.wonder.bring.mapper;

import com.wonder.bring.dto.Menu;
import com.wonder.bring.dto.SizePrice;
import com.wonder.bring.dto.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Create by YoungEun on 2018-12-29.
 */

@Mapper
public interface MenuMapper {

    /**
     * 모든 메뉴 리스트 조회
     * @return 메뉴 리스트
     */
    // storeIdx로 menuList 조회(size = 1(regular)일 때)
    @Select("SELECT MENU.menu_idx, MENU.name, MENU.photo_url, SIZE_PRICE.price, SIZE_PRICE.size FROM MENU " +
            "INNER JOIN STORES_MENU ON (MENU.menu_idx = STORES_MENU.menu_idx) " +
            "INNER JOIN SIZE_PRICE ON (MENU.menu_idx = SIZE_PRICE.menu_idx) " +
            "WHERE STORES_MENU.store_idx = #{store_idx} AND (SIZE_PRICE.size = 1 OR SIZE_PRICE.size = 4)")
    List<Menu> findMenuByStoreIdx(@Param("store_idx") final int storeIdx);

    /**
     * 메뉴 상세 정보 조회
     * @return 메뉴 상세 정보
     */
    // menu 정보 조회
    @Select("SELECT COUNT(*) FROM STORES_MENU WHERE store_idx = #{store_idx} AND menu_idx = #{menu_idx}")
    int findStoreMenu(@Param("store_idx") final int storeIdx, @Param("menu_idx") final int menuIdx);

    // size별 가격 조회
    @Select("SELECT size, price FROM SIZE_PRICE WHERE menu_idx = #{menu_idx}")
    List<SizePrice> findSizePriceByMenuIdx(@Param("menu_idx") final int menuIdx);
}

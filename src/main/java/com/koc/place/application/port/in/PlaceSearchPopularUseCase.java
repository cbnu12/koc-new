package com.koc.place.application.port.in;

import com.koc.place.domain.Place;
import org.springframework.data.domain.Page;

public interface PlaceSearchPopularUseCase {

    /**
     * 트렌드 장소 조회
     *
     * @param size 조회 갯수
     * @return 트렌드 장소
     */
    Page<Place> searchTrend(Integer size);

    /**
     * 추천 장소 조회
     *
     * @param size 조회 갯수
     * @return 추천 장소
     */
    Page<Place> searchRecommend(Integer size);

    /**
     * 상위 평점 장소 조회
     *
     * @param size 조회 갯수
     * @return 상위 평점 장소
     */
    Page<Place> searchHighScore(Integer size);
}
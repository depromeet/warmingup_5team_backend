package com.example.blog.controller

import com.example.blog.db.DummyDb
import com.example.blog.db.InMemoryDb
import com.example.blog.model.entity.*
import com.example.blog.model.response.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime

@Api(tags = ["API"])
@Controller
class HomeController {

    companion object {
        val RANGE_OF_NEAR_BY = 200.0
    }


    @ApiOperation(value = "좌표 근처 찾기", notes = "좌표 근처의 고양이, 배급소, 고양이, 보호소를 반합니다.")
    @GetMapping("/nearby", consumes = ["application/json"])
    @ResponseBody
    fun getNearBy(
        @ApiParam("x 좌표", defaultValue = "20.0", required = true) @RequestParam("x") x: Double,
        @ApiParam("y 좌표", defaultValue = "20.0", required = true) @RequestParam("y") y: Double
    ): GetNearbyResponse = GetNearbyResponse(
        GetNearbyResponse.Data(
            cats = InMemoryDb.getCats().filter {
                it.location.isNearBy(
                    RANGE_OF_NEAR_BY, Location(x, y)
                )
            }.toTypedArray(),
            distributionPoints = InMemoryDb.getDistributionPoints().filter {
                it.location.isNearBy(
                    RANGE_OF_NEAR_BY, Location(x, y)
                )
            }.toTypedArray(),
            shelters = DummyDb.getShelter().filter {
                it.location.isNearBy(
                    RANGE_OF_NEAR_BY, Location(x, y)
                )
            }.toTypedArray(),
            hospital = DummyDb.getHospital().filter {
                it.location.isNearBy(
                    RANGE_OF_NEAR_BY, Location(x, y)
                )
            }.toTypedArray()
        )
    )

    @ApiOperation(value = "최근 검색어", notes = "count만큼 최근 검색어를 반환합니다. count가 null일 경우 모두 리턴합니다.")
    @GetMapping("/search-word/recently", consumes = ["application/json"])
    @ResponseBody
    fun getRecentlySearchWords(
        @ApiParam("검색어 갯수", defaultValue = "20", required = false) @RequestParam("count") count: Int?
    ): GetRecentlySearchWordsResponse = GetRecentlySearchWordsResponse(
        data = InMemoryDb.getSearchWords()
            .let {
                if (count != null)
                    it.takeLast(count).toTypedArray()
                else it
            }
    )

    @ApiOperation(value = "연관 검색어", notes = "keyword가 포함되어있는 검색어를 반환합니다.")
    @GetMapping("/search-word/related", consumes = ["application/json"])
    @ResponseBody
    fun getRelatedSearchWords(
        @ApiParam("검색어", defaultValue = "병원", required = true) @RequestParam("keyword") keyword: String
    ): GetRelatedSearchWordsResponse = GetRelatedSearchWordsResponse(
        data = InMemoryDb.getSearchWords()
            .filter { searchWord ->
                keyword.split(" ")
                    .find {
                        searchWord.text.contains(it)
                    } != null
            }.toTypedArray()

    )

    @ApiOperation(value = "검색", notes = "keyword가 포함되어있는 고양이, 배급소, 병원, 보호소를 반환합니다.")
    @GetMapping("/search", consumes = ["application/json"])
    @ResponseBody
    fun getSearch(
        @ApiParam("검색어", defaultValue = "병원", required = true) @RequestParam("keyword") keyword: String
    ): GetSearchResponse = GetSearchResponse(
        data = GetSearchResponse.Data(
            cats = InMemoryDb.getCats()
                .filter {
                    it.name.contains(
                        keyword
                    )
                }.toTypedArray(),
            distributionPoints = InMemoryDb.getDistributionPoints()
                .filter {
                    it.name.contains(
                        keyword
                    )
                }.toTypedArray(),
            shelters = DummyDb.getShelter()
                .filter {
                    it.name.contains(
                        keyword
                    )
                }.toTypedArray(),
            hospital = DummyDb.getHospital()
                .filter {
                    it.name.contains(
                        keyword
                    )
                }.toTypedArray()
        )
    )

    @ApiOperation(value = "고양이 추가", notes = "서버에 고양이를 추가합니다.")
    @GetMapping("/cat", consumes = ["application/json"])
    @ResponseBody
    fun postCat(
        @RequestBody model: CatModel
    ): PostCatResponse = with(InMemoryDb) {
        if (model.id != null) throw IllegalArgumentException("id is must be null")
        if (model.name == null) throw IllegalArgumentException("name is must be not null")
        if (model.location == null) throw IllegalArgumentException("location is must be not null")
        if (model.images == null) throw IllegalArgumentException("images is must be not null")
        if (model.feature == null) throw IllegalArgumentException("feature is must be not null")

        val id = InMemoryDb.getLastCatId()
        val inserted = Cat(
            id = id + 1,
            name = model.name!!,
            location = model.location!!,
            images = model.images!!,
            feature = model.feature!!,
            createdAt = ZonedDateTime.now()
        )

        addCats(
            arrayOf(
                inserted
            )
        )

        return PostCatResponse(
            inserted
        )
    }

    @ApiOperation(value = "고양이 수정", notes = "서버에 존재하던 고양이를 수정합니다.")
    @PatchMapping("/cat", consumes = ["application/json"])
    @ResponseBody
    fun patchCat(
        @RequestBody model: CatModel
    ): PatchCatResponse = with(InMemoryDb) {
        model.id ?: throw IllegalArgumentException("id is must be not null")

        val original = getCats().find {
            it.id == model.id
        } ?: throw IllegalArgumentException("can not find Cat. id is ${model.id}")
        val updated = original.copy(
            name = model.name ?: original.name,
            location = model.location ?: original.location,
            images = model.images ?: original.images,
            feature = model.feature ?: original.feature
        )

        addCats(
            arrayOf(
                updated
            )
        )

        return PatchCatResponse(
            updated
        )
    }

    @ApiOperation(value = "배급소 추가", notes = "서버에 배급소를 추가합니다.")
    @GetMapping("/distribution-point", consumes = ["application/json"])
    @ResponseBody
    fun postDistributionPoint(
        @RequestBody model: DistributionPointModel
    ): PostDistributionPointResponse = with(InMemoryDb) {
        if (model.id != null) throw IllegalArgumentException("id is must be null")
        if (model.name == null) throw IllegalArgumentException("name is must be not null")
        if (model.location == null) throw IllegalArgumentException("location is must be not null")
        if (model.images == null) throw IllegalArgumentException("images is must be not null")
        if (model.feature == null) throw IllegalArgumentException("feature is must be not null")

        val id = InMemoryDb.getLastDistributionPointId() + 1
        val inserted = DistributionPoint(
            id = id,
            name = model.name!!,
            location = model.location!!,
            images = model.images!!,
            feature = model.feature!!,
            createdAt = ZonedDateTime.now()
        )

        addDistributionPoints(
            arrayOf(
                inserted
            )
        )

        return PostDistributionPointResponse(
            inserted
        )
    }

    @ApiOperation(value = "배급소 수정", notes = "서버에 존재하던 배급소를 수정합니다.")
    @PatchMapping("/distribution-point/patch", consumes = ["application/json"])
    @ResponseBody
    fun patchDistributionPoint(
        @RequestBody model: DistributionPointModel
    ): PatchDistributionPointResponse = with(InMemoryDb) {
        model.id ?: throw IllegalArgumentException("id is must be not null")

        val original = getDistributionPoints().find {
            it.id == model.id
        } ?: throw IllegalArgumentException("can not find DistributionPoint. id is ${model.id}")
        val updated = original.copy(
            name = model.name ?: original.name,
            location = model.location ?: original.location,
            images = model.images ?: original.images,
            feature = model.feature ?: original.feature
        )

        addDistributionPoints(
            arrayOf(
                updated
            )
        )

        return PatchDistributionPointResponse(
            updated
        )
    }
}
package aca.appdong.bicycle_manager

data class UserItem(
    val number: Int,
    val block: Int,
    val unit: Int,
    val barcode: Int,
    val reside: Boolean
)
/*
number 순번
block 동
unit 호수
barcode 바코드
reside 상태(거주 or 비거주)
 */
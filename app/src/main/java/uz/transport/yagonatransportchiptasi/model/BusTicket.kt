package uz.transport.yagonatransportchiptasi.model

data class BusTicket(
    val tv_company_name: String,
    val tv_start_time: String,
    val tv_finish_time: String,
    val tv_palce_start: String,
    val tv_place_finish: String,
    val tv_departure_time: String,
    val tv_time_spend: String,
    val tv_seat_num: String,
    val tv_fare: String
)

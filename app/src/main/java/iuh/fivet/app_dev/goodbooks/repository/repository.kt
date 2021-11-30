package iuh.fivet.app_dev.goodbooks.repository

import iuh.fivet.app_dev.goodbooks.api.ApiService

class repository(private val Api: ApiService) {

    suspend fun getTop100Books() = Api.getTop100Books()
}
package uk.ac.tees.mad.fintrack.domain.model

data class Transaction(
    val id :Int ,
    val title : String ,
    val amount : Double ? ,
    val type : String ,
    val category : String ,
    val date : Long ,
    val note : String ?
)

package ii_collections

fun example() {

    val result = listOf("abc", "12").flatMap { it.toList() }

    result == listOf('a', 'b', 'c', '1', '2')
}

val Customer.orderedProducts: Set<Product> get() {
    // Return all products this customer has ordered
    return this.orders.flatMap { it.products }.toSet()
}

val Shop.allOrderedProducts: Set<Product> get() {
    // Return all products that were ordered by at least one customer
    println(this.customers)

    return this.customers.flatMap {
        println("customer: $it has products ${it.orderedProducts}")
        it.orderedProducts
    }.toSet().also {
        println(it)
    }
}

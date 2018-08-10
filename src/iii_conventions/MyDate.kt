package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate) = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }

    operator fun plus(interval: TimeInterval): MyDate = addTimeIntervals(interval,1)

    operator fun plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate =
            addTimeIntervals(repeatedTimeInterval.ti,repeatedTimeInterval.n)
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)
class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var currentDate = start
            override fun hasNext(): Boolean = currentDate in this@DateRange

            override fun next(): MyDate = currentDate.also { currentDate = it.nextDay() }
        }

    }
}

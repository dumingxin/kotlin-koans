package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (year - other.year == 0) {
            if (month - other.month == 0) {
                return dayOfMonth - other.dayOfMonth
            } else {
                return month - other.month
            }
        } else {
            return year - other.year
        }
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var curDate: MyDate? = null
            override fun hasNext(): Boolean {
                if (curDate == null) {
                    return start < endInclusive
                } else {
                    return curDate!! < endInclusive
                }
            }

            override fun next(): MyDate {
                if (curDate == null) {
                    curDate = start
                    return curDate as MyDate
                } else {
                    curDate = curDate!!.nextDay()
                    return curDate as MyDate
                }

            }

        }
    }

    override fun contains(date: MyDate): Boolean {
        return start <= date && date <= endInclusive
    }
}
class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)
operator fun MyDate.plus(timeInterval: TimeInterval): MyDate {
    return this.addTimeIntervals(timeInterval,1)
}
operator fun TimeInterval.times(n:Int):RepeatedTimeInterval{
    return RepeatedTimeInterval(this,n)
}
operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
return this.addTimeIntervals(repeatedTimeInterval.ti,repeatedTimeInterval.n)
}
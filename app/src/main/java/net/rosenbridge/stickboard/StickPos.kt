package net.rosenbridge.stickboard

enum class StickPos(val x:Int, val y: Int) {
    TL(-1,-1),
    T(0,-1),
    TR(1,-1),
    L(-1,0),
    C(0,0),
    R(1,0),
    BL(-1,1),
    B(0,1),
    BR(1,1);
    fun isMain() : Boolean {
        return x == 0 && y == 0
    }
}
private val inverse = StickPos.entries.map{ v -> Pair(Pair(v.x, v.y), v)}.toMap()
fun fromDelta(dx : Int, dy : Int) : StickPos? {
    val p = Pair(dx, dy)
    return inverse.get(p)
}
package net.rosenbridge.stickboard

class StickboardLayer (val boxes : Collection<Stick>, val coordToBoxes : Map<Pair<Int, Int>, Stick> ){
    constructor(boxes: Collection<Stick>) :  this(boxes, boxes.associateBy { Pair(it.l, it.t) });
    constructor(coordToBoxes : Map<Pair<Int, Int>, Stick> ): this (coordToBoxes.values, coordToBoxes);

    companion object {
        fun merge(l : StickboardLayer, r : StickboardLayer) : StickboardLayer {
            var bothKeys = l.coordToBoxes.keys.union(r.coordToBoxes.keys);
            var bothMap : Map<Pair<Int, Int>, Stick> = bothKeys.map { Pair(it, Stick.merge(l.coordToBoxes[it],
                r.coordToBoxes[it]
            )!!) }.toMap();
            return StickboardLayer(bothMap)
        }
    }
    fun toUpper() : StickboardLayer {
        return StickboardLayer(this.boxes.map {it.toUpper()})
    }
}
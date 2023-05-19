package com.freecharge.locationservice.Utils;

import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.geom.MultiPolygon;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class MultiPolygonWrapper {

    private final MultiPolygon multiPolygon;

    @JsonCreator
    public MultiPolygonWrapper(@JsonProperty("coordinates") List<List<List<List<Double>>>> coordinates) {
        GeometryFactory factory = new GeometryFactory();
        List<Polygon> polygons = new ArrayList<>();

        for (List<List<List<Double>>> polygonCoordinates : coordinates) {
            List<Coordinate> shell = new ArrayList<>();
            for (List<List<Double>> ringCoordinates : polygonCoordinates) {
                List<Coordinate> ring = new ArrayList<>();
                for (List<Double> coord : ringCoordinates) {
                    ring.add(new Coordinate(coord.get(0), coord.get(1)));
                }
                shell.addAll(ring);
            }
            Polygon polygon = factory.createPolygon(shell.toArray(new Coordinate[0]));
            polygons.add(polygon);
        }
        log.info("Polygon {}",polygons.toArray());
        multiPolygon = factory.createMultiPolygon(polygons.toArray(new Polygon[0]));
    }

    public MultiPolygon getMultiPolygon() {
        return multiPolygon;
    }
}
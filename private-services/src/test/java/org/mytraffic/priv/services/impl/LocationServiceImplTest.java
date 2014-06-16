package org.mytraffic.priv.services.impl;

import org.junit.Before;
import org.junit.Test;
import org.mytraffic.Location;

import static org.junit.Assert.assertTrue;

public class LocationServiceImplTest {

    private LocationServiceImpl locationService;

    @Before
    public void setUp() throws Exception {
        locationService = new LocationServiceImpl();
        locationService.setPointWithinRouteTolerance(10);
    }

    @Test
    public void testIsPointWithinRoute() throws Exception {
        Location mallInter = new Location(10.007257, -84.212159);
        Location aeropuerto = new Location(9.999243, -84.197471);
        Location realCariari = new Location(9.980076, -84.160908);
        Location castella = new Location(9.968432, -84.136703);
        Location hospitalMexico = new Location(9.952572, -84.113515);
        String routePolyline = "y}b|@hy~`OxApIxHwAfEo@bCg@jC_@fAQt@QdAUlAYzAi@h@Uh@[lAy@`Au@bA_ApB_BbEgDnDuClDmCbB" +
                "sAPWzBcBxHeGpBkBfA_AVS`@q@Xs@Pu@Du@Eu@Io@}@qB_@gAKgABo@Fk@b@sAb@cAvA}Bh@y@^cAP}@F{AG}@SmA_AiDuAkF" +
                "]gBc@yC[}BIaCIcHKuJGqEKcCQiCk@uFw@cF_CyNmBsLq@iE[eBSkBIeB?eBJyCPiBTsA|@_DxAeD`E}I|EoKt@_Bd@y@hBgC" +
                "fBiBz@u@bBiA`B_A|B_AhEoAzOeEtc@uLbO}DlF{A~As@fAk@~AcAx@o@l@g@dAoAhAaBj@eAn@uAvB_FnE}JtM_ZdDqHz@qB" +
                "p@iBjAuD|AsFnDuMrGyUtAsEjByExEqK`BsDv@uBl@{Aj@kAlA}BnAwBv@eB|AcEjD}HbCqFxGeOtQea@~CgHvByE`BmD|AgC" +
                "~AqBfAkArBeBjBsApBmAnD{BvGaEnEsC`MwH~DeCnEuC~BqAjD}BdA}@lAsAbIwOlA_CZq@p@_BXqAP_BHmBPsJJcBP}AZiAh" +
                "@gAr@y@z@s@~@a@hAWfAKtNSvEKtDGxAAASVoEd@_JXkIf@wJd@aK}HoB_@vDm@nKYjI";

        assertTrue(locationService.isLocationOnRoute(mallInter, routePolyline));
        assertTrue(locationService.isLocationOnRoute(aeropuerto, routePolyline));
        assertTrue(locationService.isLocationOnRoute(realCariari, routePolyline));
        assertTrue(locationService.isLocationOnRoute(castella, routePolyline));
        assertTrue(locationService.isLocationOnRoute(hospitalMexico, routePolyline));
    }

}

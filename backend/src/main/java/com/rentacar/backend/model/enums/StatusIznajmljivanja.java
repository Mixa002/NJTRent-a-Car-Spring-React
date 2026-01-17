package com.rentacar.backend.model.enums;

public enum StatusIznajmljivanja {
    CEKA_ODOBRENJE,//klijent poslao zahtev, radnik odobrava
    ODOBRENO,//radnik odobrio
    AKTIVNO,//u toku je
    ZAVRSENO,//zavrsilo se, za arhiviranje
    OTKAZANO//klijent je otkazao iznajmljivanje
}

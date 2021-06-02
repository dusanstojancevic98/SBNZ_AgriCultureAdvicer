export class VremenskiPodaci {
    constructor() {
    }
    brojSuncanihDana = NaN;

    padavine = NaN;

    prosecnaGodisnjaTemperatura = NaN;


}

export class Usev {
    constructor() {
    }

    id = NaN;

    naziv = "";

    opis = "";

    tipUseva = "";

    podTipUseva = "";


}

export class Zemljiste {

    constructor() {
    }

    povrsina = NaN;


    sirina = NaN;


    duzina = NaN;


    nadmVisina = NaN;


    phVrednost = NaN;


    procenatHumusa = NaN;


    procenatKarbonata = NaN;


    nagibZemljista = NaN;


    lokacija = "";


    nadmorskaVisina = NaN;


    tipZemljista = "";

    prethodniUsevi = [];

}


export class Konfiguracija {

    constructor() {
    }

    naziv = "";

    zemljiste =  new Zemljiste();

    vremenskiPodaci = new VremenskiPodaci();


}

export class Razvoj {

    constructor() {
    }

    budzet = NaN;

    pocetakSadjenja = null;


    konfiguracija = new Konfiguracija();


}

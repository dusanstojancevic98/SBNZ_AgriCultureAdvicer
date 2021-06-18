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

    tipUseva = null;

    podTipUseva = null;


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


    lokacija = null;


    nadmorskaVisina = NaN;


    tipZemljista = null;

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

    naziv = "";

    budzet = NaN;

    pocetakSadjenja = null;

    ponudaUseva = {
        ponude: []
    }

    konfiguracija = new Konfiguracija();


}

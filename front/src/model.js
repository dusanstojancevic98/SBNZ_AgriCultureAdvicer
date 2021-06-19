export class VremenskiPodaci {
    constructor() {
    }

    brojSuncanihDana = NaN;

    padavine = NaN;

    prosecnaGodisnjaTemperatura = NaN;


}

export class StanjeZemljista {
    id;

    datum = null;

    procenatVlage = 0;


    stanje = "";


    prethodnoStanje = null;
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

export class UsevPodaci {
    id;

    usev;

    stanjaUseva = [];

    preporucenaZastita;

    pocetakUzgoja;

    krajUzgoja;

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

    trenutnoStanjeZemljista = null;

    stanjaZemljista = [];

    trenutanUsev = null;

    prethodniUsevi = [];

}


export class Konfiguracija {

    constructor() {
    }

    naziv = "";

    zemljiste = new Zemljiste();

    vremenskiPodaci = new VremenskiPodaci();


}

export class Razvoj {

    constructor() {
    }

    naziv = "";

    budzet = NaN;

    ponudaUseva = {
        ponude: []
    }

    konfiguracija = new Konfiguracija();


}

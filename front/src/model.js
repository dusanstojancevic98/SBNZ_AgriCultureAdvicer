export class VremenskiPodaci {
    constructor() {
    }
    brojSuncanihDana: number;

    padavine: number;

    prosecnaGodisnjaTemperatura: number;


}

export class Usev {
    constructor() {
    }

    id: number;

    naziv: string;

    opis: string;

    tipUseva: string;

    podTipUseva: string;


}

export class Zemljiste {

    constructor() {
    }

    povrsina: number;


    sirina: number;


    duzina: number;


    nadmVisina: number;


    phVrednost: number;


    procenatHumusa: number;


    procenatKarbonata: number;


    nagibZemljista: number;


    lokacija: string;


    nadmorskaVisina: number;


    tipZemljista: string;

    prethodniUsevi: Usev[];

}


export class Konfiguracija {

    constructor() {
    }

    naziv: string;

    zemljiste: Zemljiste;

    vremenskiPodaci: VremenskiPodaci;


}

export class Razvoj {

    constructor() {
    }

    budzet: number;

    pocetakSadjenja: Date;


    konfiguracija: Konfiguracija;


}

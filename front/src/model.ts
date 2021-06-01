export class VremenskiPodaci {
    constructor() {
    }

    _brojSuncanihDana: number;

    _padavine: number;

    _prosecnaGodisnjaTemperatura: number;


    get brojSuncanihDana(): number {
        return this._brojSuncanihDana;
    }

    set brojSuncanihDana(value: number) {
        this._brojSuncanihDana = value;
    }

    get padavine(): number {
        return this._padavine;
    }

    set padavine(value: number) {
        this._padavine = value;
    }

    get prosecnaGodisnjaTemperatura(): number {
        return this._prosecnaGodisnjaTemperatura;
    }

    set prosecnaGodisnjaTemperatura(value: number) {
        this._prosecnaGodisnjaTemperatura = value;
    }
}

export class Usev {
    constructor() {
    }

    _id: number;

    _naziv: string;

    _opis: string;

    _tipUseva: string;

    _podTipUseva: string;


    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get naziv(): string {
        return this._naziv;
    }

    set naziv(value: string) {
        this._naziv = value;
    }

    get opis(): string {
        return this._opis;
    }

    set opis(value: string) {
        this._opis = value;
    }

    get tipUseva(): string {
        return this._tipUseva;
    }

    set tipUseva(value: string) {
        this._tipUseva = value;
    }

    get podTipUseva(): string {
        return this._podTipUseva;
    }

    set podTipUseva(value: string) {
        this._podTipUseva = value;
    }
}

export class Zemljiste {

    constructor() {
    }

    _povrsina: number;


    _sirina: number;


    _duzina: number;


    _nadmVisina: number;


    _phVrednost: number;


    _procenatHumusa: number;


    _procenatKarbonata: number;


    _nagibZemljista: number;


    _lokacija: string;


    _nadmorskaVisina: number;


    _prethodniUsevi: Usev[];


    _tipZemljista: string;


    get povrsina(): number {
        return this._povrsina;
    }

    set povrsina(value: number) {
        this._povrsina = value;
    }

    get sirina(): number {
        return this._sirina;
    }

    set sirina(value: number) {
        this._sirina = value;
    }

    get duzina(): number {
        return this._duzina;
    }

    set duzina(value: number) {
        this._duzina = value;
    }

    get nadmVisina(): number {
        return this._nadmVisina;
    }

    set nadmVisina(value: number) {
        this._nadmVisina = value;
    }

    get phVrednost(): number {
        return this._phVrednost;
    }

    set phVrednost(value: number) {
        this._phVrednost = value;
    }

    get procenatHumusa(): number {
        return this._procenatHumusa;
    }

    set procenatHumusa(value: number) {
        this._procenatHumusa = value;
    }

    get procenatKarbonata(): number {
        return this._procenatKarbonata;
    }

    set procenatKarbonata(value: number) {
        this._procenatKarbonata = value;
    }

    get nagibZemljista(): number {
        return this._nagibZemljista;
    }

    set nagibZemljista(value: number) {
        this._nagibZemljista = value;
    }

    get lokacija(): string {
        return this._lokacija;
    }

    set lokacija(value: string) {
        this._lokacija = value;
    }

    get nadmorskaVisina(): number {
        return this._nadmorskaVisina;
    }

    set nadmorskaVisina(value: number) {
        this._nadmorskaVisina = value;
    }

    get prethodniUsevi(): Usev[] {
        return this._prethodniUsevi;
    }

    set prethodniUsevi(value: Usev[]) {
        this._prethodniUsevi = value;
    }

    get tipZemljista(): string {
        return this._tipZemljista;
    }

    set tipZemljista(value: string) {
        this._tipZemljista = value;
    }
}


export class Konfiguracija {

    constructor() {
    }

    _naziv: string;

    _zemljiste: Zemljiste;

    _vremenskiPodaci: VremenskiPodaci;


    get naziv(): string {
        return this._naziv;
    }

    set naziv(value: string) {
        this._naziv = value;
    }

    get zemljiste(): Zemljiste {
        return this._zemljiste;
    }

    set zemljiste(value: Zemljiste) {
        this._zemljiste = value;
    }

    get vremenskiPodaci(): VremenskiPodaci {
        return this._vremenskiPodaci;
    }

    set vremenskiPodaci(value: VremenskiPodaci) {
        this._vremenskiPodaci = value;
    }
}

export class Razvoj {

    constructor() {
    }

    _budzet: number;

    _pocetakSadjenja: Date;


    _konfiguracija: Konfiguracija;


    get budzet(): number {
        return this._budzet;
    }

    set budzet(value: number) {
        this._budzet = value;
    }

    get pocetakSadjenja(): Date {
        return this._pocetakSadjenja;
    }

    set pocetakSadjenja(value: Date) {
        this._pocetakSadjenja = value;
    }

    get konfiguracija(): Konfiguracija {
        return this._konfiguracija;
    }

    set konfiguracija(value: Konfiguracija) {
        this._konfiguracija = value;
    }
}

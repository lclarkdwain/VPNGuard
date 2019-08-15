package dev.anullihate.vpnguard.internationalization;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public enum CountryCode {
    UNDEFINED("Undefined", (String)null, -1, CountryCode.Assignment.USER_ASSIGNED) {
        public Locale toLocale() {
            return LocaleCode.undefined.toLocale();
        }
    },
    AC("Ascension Island", "ASC", -1, CountryCode.Assignment.EXCEPTIONALLY_RESERVED),
    AD("Andorra", "AND", 20, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AE("United Arab Emirates", "ARE", 784, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AF("Afghanistan", "AFG", 4, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AG("Antigua and Barbuda", "ATG", 28, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AI("Anguilla", "AIA", 660, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AL("Albania", "ALB", 8, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AM("Armenia", "ARM", 51, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AN("Netherlands Antilles", "ANT", 530, CountryCode.Assignment.TRANSITIONALLY_RESERVED),
    AO("Angola", "AGO", 24, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AQ("Antarctica", "ATA", 10, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AR("Argentina", "ARG", 32, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AS("American Samoa", "ASM", 16, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AT("Austria", "AUT", 40, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AU("Australia", "AUS", 36, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AW("Aruba", "ABW", 533, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AX("Åland Islands", "ALA", 248, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    AZ("Azerbaijan", "AZE", 31, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BA("Bosnia and Herzegovina", "BIH", 70, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BB("Barbados", "BRB", 52, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BD("Bangladesh", "BGD", 50, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BE("Belgium", "BEL", 56, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BF("Burkina Faso", "BFA", 854, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BG("Bulgaria", "BGR", 100, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BH("Bahrain", "BHR", 48, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BI("Burundi", "BDI", 108, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BJ("Benin", "BEN", 204, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BL("Saint Barthélemy", "BLM", 652, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BM("Bermuda", "BMU", 60, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BN("Brunei Darussalam", "BRN", 96, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BO("Bolivia, Plurinational State of", "BOL", 68, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BQ("Bonaire, Sint Eustatius and Saba", "BES", 535, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BR("Brazil", "BRA", 76, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BS("Bahamas", "BHS", 44, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BT("Bhutan", "BTN", 64, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BU("Burma", "BUR", 104, CountryCode.Assignment.TRANSITIONALLY_RESERVED),
    BV("Bouvet Island", "BVT", 74, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BW("Botswana", "BWA", 72, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BY("Belarus", "BLR", 112, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    BZ("Belize", "BLZ", 84, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CA("Canada", "CAN", 124, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.CANADA;
        }
    },
    CC("Cocos (Keeling) Islands", "CCK", 166, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CD("Congo, the Democratic Republic of the", "COD", 180, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CF("Central African Republic", "CAF", 140, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CG("Congo", "COG", 178, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CH("Switzerland", "CHE", 756, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CI("Côte d'Ivoire", "CIV", 384, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CK("Cook Islands", "COK", 184, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CL("Chile", "CHL", 152, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CM("Cameroon", "CMR", 120, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CN("China", "CHN", 156, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.CHINA;
        }
    },
    CO("Colombia", "COL", 170, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CP("Clipperton Island", "CPT", -1, CountryCode.Assignment.EXCEPTIONALLY_RESERVED),
    CR("Costa Rica", "CRI", 188, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CS("Serbia and Montenegro", "SCG", 891, CountryCode.Assignment.TRANSITIONALLY_RESERVED),
    CU("Cuba", "CUB", 192, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CV("Cape Verde", "CPV", 132, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CW("Curaçao", "CUW", 531, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CX("Christmas Island", "CXR", 162, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CY("Cyprus", "CYP", 196, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    CZ("Czech Republic", "CZE", 203, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    DE("Germany", "DEU", 276, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.GERMANY;
        }
    },
    DG("Diego Garcia", "DGA", -1, CountryCode.Assignment.EXCEPTIONALLY_RESERVED),
    DJ("Djibouti", "DJI", 262, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    DK("Denmark", "DNK", 208, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    DM("Dominica", "DMA", 212, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    DO("Dominican Republic", "DOM", 214, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    DZ("Algeria", "DZA", 12, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    EA("Ceuta, Melilla", (String)null, -1, CountryCode.Assignment.EXCEPTIONALLY_RESERVED),
    EC("Ecuador", "ECU", 218, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    EE("Estonia", "EST", 233, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    EG("Egypt", "EGY", 818, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    EH("Western Sahara", "ESH", 732, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    ER("Eritrea", "ERI", 232, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    ES("Spain", "ESP", 724, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    ET("Ethiopia", "ETH", 231, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    EU("European Union", (String)null, -1, CountryCode.Assignment.EXCEPTIONALLY_RESERVED),
    FI("Finland", "FIN", 246, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    FJ("Fiji", "FJI", 242, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    FK("Falkland Islands (Malvinas)", "FLK", 238, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    FM("Micronesia, Federated States of", "FSM", 583, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    FO("Faroe Islands", "FRO", 234, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    FR("France", "FRA", 250, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.FRANCE;
        }
    },
    FX("France, Metropolitan", "FXX", 249, CountryCode.Assignment.EXCEPTIONALLY_RESERVED),
    GA("Gabon", "GAB", 266, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GB("United Kingdom", "GBR", 826, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.UK;
        }
    },
    GD("Grenada", "GRD", 308, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GE("Georgia", "GEO", 268, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GF("French Guiana", "GUF", 254, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GG("Guernsey", "GGY", 831, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GH("Ghana", "GHA", 288, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GI("Gibraltar", "GIB", 292, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GL("Greenland", "GRL", 304, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GM("Gambia", "GMB", 270, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GN("Guinea", "GIN", 324, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GP("Guadeloupe", "GLP", 312, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GQ("Equatorial Guinea", "GNQ", 226, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GR("Greece", "GRC", 300, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GS("South Georgia and the South Sandwich Islands", "SGS", 239, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GT("Guatemala", "GTM", 320, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GU("Guam", "GUM", 316, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GW("Guinea-Bissau", "GNB", 624, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    GY("Guyana", "GUY", 328, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    HK("Hong Kong", "HKG", 344, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    HM("Heard Island and McDonald Islands", "HMD", 334, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    HN("Honduras", "HND", 340, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    HR("Croatia", "HRV", 191, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    HT("Haiti", "HTI", 332, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    HU("Hungary", "HUN", 348, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IC("Canary Islands", (String)null, -1, CountryCode.Assignment.EXCEPTIONALLY_RESERVED),
    ID("Indonesia", "IDN", 360, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IE("Ireland", "IRL", 372, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IL("Israel", "ISR", 376, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IM("Isle of Man", "IMN", 833, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IN("India", "IND", 356, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IO("British Indian Ocean Territory", "IOT", 86, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IQ("Iraq", "IRQ", 368, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IR("Iran, Islamic Republic of", "IRN", 364, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IS("Iceland", "ISL", 352, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    IT("Italy", "ITA", 380, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.ITALY;
        }
    },
    JE("Jersey", "JEY", 832, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    JM("Jamaica", "JAM", 388, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    JO("Jordan", "JOR", 400, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    JP("Japan", "JPN", 392, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.JAPAN;
        }
    },
    KE("Kenya", "KEN", 404, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    KG("Kyrgyzstan", "KGZ", 417, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    KH("Cambodia", "KHM", 116, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    KI("Kiribati", "KIR", 296, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    KM("Comoros", "COM", 174, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    KN("Saint Kitts and Nevis", "KNA", 659, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    KP("Korea, Democratic People's Republic of", "PRK", 408, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    KR("Korea, Republic of", "KOR", 410, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.KOREA;
        }
    },
    KW("Kuwait", "KWT", 414, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    KY("Cayman Islands", "CYM", 136, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    KZ("Kazakhstan", "KAZ", 398, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LA("Lao People's Democratic Republic", "LAO", 418, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LB("Lebanon", "LBN", 422, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LC("Saint Lucia", "LCA", 662, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LI("Liechtenstein", "LIE", 438, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LK("Sri Lanka", "LKA", 144, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LR("Liberia", "LBR", 430, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LS("Lesotho", "LSO", 426, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LT("Lithuania", "LTU", 440, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LU("Luxembourg", "LUX", 442, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LV("Latvia", "LVA", 428, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    LY("Libya", "LBY", 434, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MA("Morocco", "MAR", 504, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MC("Monaco", "MCO", 492, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MD("Moldova, Republic of", "MDA", 498, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    ME("Montenegro", "MNE", 499, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MF("Saint Martin (French part)", "MAF", 663, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MG("Madagascar", "MDG", 450, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MH("Marshall Islands", "MHL", 584, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MK("Macedonia, the former Yugoslav Republic of", "MKD", 807, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    ML("Mali", "MLI", 466, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MM("Myanmar", "MMR", 104, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MN("Mongolia", "MNG", 496, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MO("Macao", "MAC", 446, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MP("Northern Mariana Islands", "MNP", 580, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MQ("Martinique", "MTQ", 474, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MR("Mauritania", "MRT", 478, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MS("Montserrat", "MSR", 500, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MT("Malta", "MLT", 470, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MU("Mauritius", "MUS", 480, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MV("Maldives", "MDV", 462, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MW("Malawi", "MWI", 454, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MX("Mexico", "MEX", 484, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MY("Malaysia", "MYS", 458, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    MZ("Mozambique", "MOZ", 508, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NA("Namibia", "NAM", 516, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NC("New Caledonia", "NCL", 540, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NE("Niger", "NER", 562, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NF("Norfolk Island", "NFK", 574, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NG("Nigeria", "NGA", 566, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NI("Nicaragua", "NIC", 558, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NL("Netherlands", "NLD", 528, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NO("Norway", "NOR", 578, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NP("Nepal", "NPL", 524, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NR("Nauru", "NRU", 520, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NT("Neutral Zone", "NTZ", 536, CountryCode.Assignment.TRANSITIONALLY_RESERVED),
    NU("Niue", "NIU", 570, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    NZ("New Zealand", "NZL", 554, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    OM("Oman", "OMN", 512, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PA("Panama", "PAN", 591, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PE("Peru", "PER", 604, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PF("French Polynesia", "PYF", 258, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PG("Papua New Guinea", "PNG", 598, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PH("Philippines", "PHL", 608, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PK("Pakistan", "PAK", 586, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PL("Poland", "POL", 616, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PM("Saint Pierre and Miquelon", "SPM", 666, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PN("Pitcairn", "PCN", 612, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PR("Puerto Rico", "PRI", 630, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PS("Palestine, State of", "PSE", 275, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PT("Portugal", "PRT", 620, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PW("Palau", "PLW", 585, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    PY("Paraguay", "PRY", 600, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    QA("Qatar", "QAT", 634, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    RE("Réunion", "REU", 638, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    RO("Romania", "ROU", 642, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    RS("Serbia", "SRB", 688, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    RU("Russian Federation", "RUS", 643, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    RW("Rwanda", "RWA", 646, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SA("Saudi Arabia", "SAU", 682, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SB("Solomon Islands", "SLB", 90, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SC("Seychelles", "SYC", 690, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SD("Sudan", "SDN", 729, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SE("Sweden", "SWE", 752, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SF("Finland", "FIN", 246, CountryCode.Assignment.TRANSITIONALLY_RESERVED),
    SG("Singapore", "SGP", 702, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SH("Saint Helena, Ascension and Tristan da Cunha", "SHN", 654, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SI("Slovenia", "SVN", 705, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SJ("Svalbard and Jan Mayen", "SJM", 744, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SK("Slovakia", "SVK", 703, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SL("Sierra Leone", "SLE", 694, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SM("San Marino", "SMR", 674, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SN("Senegal", "SEN", 686, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SO("Somalia", "SOM", 706, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SR("Suriname", "SUR", 740, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SS("South Sudan", "SSD", 728, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    ST("Sao Tome and Principe", "STP", 678, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SU("USSR", "SUN", 810, CountryCode.Assignment.EXCEPTIONALLY_RESERVED),
    SV("El Salvador", "SLV", 222, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SX("Sint Maarten (Dutch part)", "SXM", 534, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SY("Syrian Arab Republic", "SYR", 760, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    SZ("Swaziland", "SWZ", 748, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TA("Tristan da Cunha", "TAA", -1, CountryCode.Assignment.EXCEPTIONALLY_RESERVED),
    TC("Turks and Caicos Islands", "TCA", 796, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TD("Chad", "TCD", 148, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TF("French Southern Territories", "ATF", 260, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TG("Togo", "TGO", 768, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TH("Thailand", "THA", 764, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TJ("Tajikistan", "TJK", 762, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TK("Tokelau", "TKL", 772, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TL("Timor-Leste", "TLS", 626, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TM("Turkmenistan", "TKM", 795, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TN("Tunisia", "TUN", 788, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TO("Tonga", "TON", 776, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TP("East Timor", "TMP", 626, CountryCode.Assignment.TRANSITIONALLY_RESERVED),
    TR("Turkey", "TUR", 792, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TT("Trinidad and Tobago", "TTO", 780, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TV("Tuvalu", "TUV", 798, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    TW("Taiwan, Province of China", "TWN", 158, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.TAIWAN;
        }
    },
    TZ("Tanzania, United Republic of", "TZA", 834, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    UA("Ukraine", "UKR", 804, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    UG("Uganda", "UGA", 800, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    UK("United Kingdom", (String)null, 826, CountryCode.Assignment.EXCEPTIONALLY_RESERVED) {
        public Locale toLocale() {
            return Locale.UK;
        }
    },
    UM("United States Minor Outlying Islands", "UMI", 581, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    US("United States", "USA", 840, CountryCode.Assignment.OFFICIALLY_ASSIGNED) {
        public Locale toLocale() {
            return Locale.US;
        }
    },
    UY("Uruguay", "URY", 858, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    UZ("Uzbekistan", "UZB", 860, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    VA("Holy See (Vatican City State)", "VAT", 336, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    VC("Saint Vincent and the Grenadines", "VCT", 670, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    VE("Venezuela, Bolivarian Republic of", "VEN", 862, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    VG("Virgin Islands, British", "VGB", 92, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    VI("Virgin Islands, U.S.", "VIR", 850, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    VN("Viet Nam", "VNM", 704, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    VU("Vanuatu", "VUT", 548, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    WF("Wallis and Futuna", "WLF", 876, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    WS("Samoa", "WSM", 882, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    XK("Kosovo, Republic of", "XXK", -1, CountryCode.Assignment.USER_ASSIGNED),
    YE("Yemen", "YEM", 887, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    YT("Mayotte", "MYT", 175, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    YU("Yugoslavia", "YUG", 890, CountryCode.Assignment.TRANSITIONALLY_RESERVED),
    ZA("South Africa", "ZAF", 710, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    ZM("Zambia", "ZMB", 894, CountryCode.Assignment.OFFICIALLY_ASSIGNED),
    ZR("Zaire", "ZAR", 180, CountryCode.Assignment.TRANSITIONALLY_RESERVED),
    ZW("Zimbabwe", "ZWE", 716, CountryCode.Assignment.OFFICIALLY_ASSIGNED);

    private static final Map<String, CountryCode> alpha3Map = new HashMap();
    private static final Map<String, CountryCode> alpha4Map = new HashMap();
    private static final Map<Integer, CountryCode> numericMap = new HashMap();
    private final String name;
    private final String alpha3;
    private final int numeric;
    private final CountryCode.Assignment assignment;

    private CountryCode(String name, String alpha3, int numeric, CountryCode.Assignment assignment) {
        this.name = name;
        this.alpha3 = alpha3;
        this.numeric = numeric;
        this.assignment = assignment;
    }

    public String getName() {
        return this.name;
    }

    public String getAlpha2() {
        return this.name();
    }

    public String getAlpha3() {
        return this.alpha3;
    }

    public int getNumeric() {
        return this.numeric;
    }

    public CountryCode.Assignment getAssignment() {
        return this.assignment;
    }

    public Locale toLocale() {
        return new Locale("", this.name());
    }

    public Currency getCurrency() {
        try {
            return Currency.getInstance(this.toLocale());
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    public static CountryCode getByCode(String code) {
        return getByCode(code, true);
    }

    public static CountryCode getByCodeIgnoreCase(String code) {
        return getByCode(code, false);
    }

    public static CountryCode getByCode(String code, boolean caseSensitive) {
        if (code == null) {
            return null;
        } else {
            switch(code.length()) {
                case 2:
                    code = canonicalize(code, caseSensitive);
                    return getByAlpha2Code(code);
                case 3:
                    code = canonicalize(code, caseSensitive);
                    return getByAlpha3Code(code);
                case 4:
                    code = canonicalize(code, caseSensitive);
                    return getByAlpha4Code(code);
                case 9:
                    code = canonicalize(code, caseSensitive);
                    if ("UNDEFINED".equals(code)) {
                        return UNDEFINED;
                    }
                case 5:
                case 6:
                case 7:
                case 8:
                default:
                    return null;
            }
        }
    }

    public static CountryCode getByLocale(Locale locale) {
        if (locale == null) {
            return null;
        } else {
            String country = locale.getCountry();
            return country != null && country.length() != 0 ? getByCode(country, true) : UNDEFINED;
        }
    }

    static String canonicalize(String code, boolean caseSensitive) {
        if (code != null && code.length() != 0) {
            return caseSensitive ? code : code.toUpperCase();
        } else {
            return null;
        }
    }

    private static CountryCode getByAlpha2Code(String code) {
        try {
            return (CountryCode)Enum.valueOf(CountryCode.class, code);
        } catch (IllegalArgumentException var2) {
            return null;
        }
    }

    private static CountryCode getByAlpha3Code(String code) {
        return (CountryCode)alpha3Map.get(code);
    }

    private static CountryCode getByAlpha4Code(String code) {
        return (CountryCode)alpha4Map.get(code);
    }

    public static CountryCode getByCode(int code) {
        return code <= 0 ? null : (CountryCode)numericMap.get(code);
    }

    public static List<CountryCode> findByName(String regex) {
        if (regex == null) {
            throw new IllegalArgumentException("regex is null.");
        } else {
            Pattern pattern = Pattern.compile(regex);
            return findByName(pattern);
        }
    }

    public static List<CountryCode> findByName(Pattern pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("pattern is null.");
        } else {
            List<CountryCode> list = new ArrayList();
            CountryCode[] var2 = values();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                CountryCode entry = var2[var4];
                if (pattern.matcher(entry.getName()).matches()) {
                    list.add(entry);
                }
            }

            return list;
        }
    }

    static {
        CountryCode[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            CountryCode cc = var0[var2];
            if (cc.getAlpha3() != null) {
                alpha3Map.put(cc.getAlpha3(), cc);
            }

            if (cc.getNumeric() != -1) {
                numericMap.put(cc.getNumeric(), cc);
            }
        }

        alpha3Map.put("FIN", FI);
        alpha4Map.put("ANHH", AN);
        alpha4Map.put("BUMM", BU);
        alpha4Map.put("CSXX", CS);
        alpha4Map.put("NTHH", NT);
        alpha4Map.put("TPTL", TP);
        alpha4Map.put("YUCS", YU);
        alpha4Map.put("ZRCD", ZR);
        numericMap.put(104, MM);
        numericMap.put(180, CD);
        numericMap.put(246, FI);
        numericMap.put(826, GB);
        numericMap.put(626, TL);
    }

    public static enum Assignment {
        OFFICIALLY_ASSIGNED,
        USER_ASSIGNED,
        EXCEPTIONALLY_RESERVED,
        TRANSITIONALLY_RESERVED,
        INDETERMINATELY_RESERVED,
        NOT_USED;

        private Assignment() {
        }
    }
}

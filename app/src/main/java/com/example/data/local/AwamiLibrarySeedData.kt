package com.example.data.local

import com.example.data.local.entity.BookEntity
import com.example.data.local.entity.ChapterEntity

object AwamiLibrarySeedData {

    fun getInitialBooks(): List<BookEntity> = listOf(
        BookEntity(
            id = "shah-jo-risalo",
            title = "Shah Jo Risalo",
            titleNative = "شاه جو رسالو",
            author = "Shah Abdul Latif Bhittai",
            authorNative = "شاه عبداللطيف ڀٽائي",
            category = "Poetry & Sufism",
            language = "Sindhi",
            description = "The immortal Sufi magnum opus of Sindh. A celebration of divine unity, human dignity, love, freedom, and the resilience of the common people across the Indus valley.",
            publicationYear = "1752 (Compiled)",
            pagesCount = 480,
            totalChapters = 4,
            isDownloaded = true,
            isFavorite = true,
            coverColorHex = 0xFF144534L,
            coverIconType = "POETRY"
        ),
        BookEntity(
            id = "diwan-e-ghalib",
            title = "Diwan-e-Ghalib",
            titleNative = "دیوانِ غالب",
            author = "Mirza Asadullah Khan Ghalib",
            authorNative = "مرزا اسد اللہ خان غالب",
            category = "Classical Poetry",
            language = "Urdu",
            description = "The definitive anthology of Mirza Ghalib's Urdu ghazals. Profound existential contemplation, unparalleled linguistic wit, and timeless philosophical verses.",
            publicationYear = "1841",
            pagesCount = 320,
            totalChapters = 3,
            isDownloaded = true,
            isFavorite = true,
            coverColorHex = 0xFFB8782BL,
            coverIconType = "CLASSIC"
        ),
        BookEntity(
            id = "kulliyat-e-iqbal",
            title = "Kulliyat-e-Iqbal: Selected Works",
            titleNative = "کلیاتِ اقبال",
            author = "Dr. Allama Muhammad Iqbal",
            authorNative = "علامہ محمد اقبال",
            category = "Philosophy & Thought",
            language = "Urdu",
            description = "Anthology containing masterpiece poems from Bang-e-Dra, Bal-e-Jibril, and Zarb-e-Kaleem. Inspires self-realization (Khudi), intellectual renewal, and moral courage.",
            publicationYear = "1924-1936",
            pagesCount = 410,
            totalChapters = 3,
            isDownloaded = true,
            isFavorite = false,
            coverColorHex = 0xFF26556BL,
            coverIconType = "PHILOSOPHY"
        ),
        BookEntity(
            id = "history-of-sindh",
            title = "Indus Heritage: History of Sindh",
            titleNative = "سنڌ جي تاريخ ۽ تهذيب",
            author = "Dr. U.M. Daudpota & Historians",
            authorNative = "ڊاڪٽر عمر بن محمد دائودپوٽو",
            category = "History & Heritage",
            language = "English & Sindhi",
            description = "A scholarly exploration of the five-thousand-year heritage of the Indus River valley, from Bronze Age Mohenjo-daro through medieval kingdoms to modern cultural enlightenment.",
            publicationYear = "1938 (Revised)",
            pagesCount = 360,
            totalChapters = 3,
            isDownloaded = true,
            isFavorite = true,
            coverColorHex = 0xFF8C3B2BL,
            coverIconType = "HISTORY"
        ),
        BookEntity(
            id = "sachal-sarmast",
            title = "Risalo Sachal Sarmast",
            titleNative = "سچل سرمست جو ڪلام",
            author = "Sachal Sarmast",
            authorNative = "سچل سرمست",
            category = "Poetry & Sufism",
            language = "Sindhi",
            description = "Ecstatic verses from Daraza Sharif by the 'Sarmast' (intoxicated by divine love). Known as Haft-Zaban (Master of Seven Languages), preaching fearless universal brotherhood.",
            publicationYear = "1826 (Compiled)",
            pagesCount = 280,
            totalChapters = 2,
            isDownloaded = true,
            isFavorite = false,
            coverColorHex = 0xFF4A3464L,
            coverIconType = "POETRY"
        ),
        BookEntity(
            id = "muqaddimah",
            title = "The Muqaddimah (Selected Chapters)",
            titleNative = "مقدمه ابن خلدون",
            author = "Ibn Khaldun",
            authorNative = "ابن خلدون",
            category = "Philosophy & Thought",
            language = "English & Urdu",
            description = "Foundational treatise on historiography, sociology, economics, and civilization. Examines the cyclical rise and fall of nations through social cohesion (Asabiyyah).",
            publicationYear = "1377",
            pagesCount = 520,
            totalChapters = 3,
            isDownloaded = true,
            isFavorite = false,
            coverColorHex = 0xFF35524AL,
            coverIconType = "PHILOSOPHY"
        ),
        BookEntity(
            id = "public-governance",
            title = "Public Administration & Awami Welfare",
            titleNative = "عوامي حڪمراني ۽ انتظاميا",
            author = "Awami Public Policy Forum",
            authorNative = "عوامي پبلڪ پاليسي فورم",
            category = "Civics & Governance",
            language = "English & Urdu",
            description = "A comprehensive handbook on citizen rights, ethical public service, decentralized local governance, and progressive economic stewardship for civil society.",
            publicationYear = "2024",
            pagesCount = 240,
            totalChapters = 2,
            isDownloaded = true,
            isFavorite = false,
            coverColorHex = 0xFF2D4B73L,
            coverIconType = "CIVIC"
        ),
        BookEntity(
            id = "sindhi-folk-tales",
            title = "Folk Tales of the Indus Valley",
            titleNative = "سنڌ جون مشهور لوڪ ڪهاڻيون",
            author = "Indus Heritage Preservation Council",
            authorNative = "سنڌي ادبي بورڊ ريسرچ",
            category = "Literature & Lore",
            language = "Sindhi & English",
            description = "Classic folk romances and moral parables of the Indus soil: Umar Marvi, Sassui Punhun, Sohni Mehar, and Momal Rano, accompanied by cultural notes.",
            publicationYear = "1962",
            pagesCount = 210,
            totalChapters = 3,
            isDownloaded = true,
            isFavorite = false,
            coverColorHex = 0xFFA0522DL,
            coverIconType = "CLASSIC"
        )
    )

    fun getInitialChapters(): List<ChapterEntity> = listOf(
        // Shah Jo Risalo Chapters
        ChapterEntity(
            id = "shah-jo-risalo_ch1",
            bookId = "shah-jo-risalo",
            chapterIndex = 0,
            title = "Sur Kalyan - Dastan 1",
            titleNative = "سر ڪلياڻ - داستان پهريون",
            subtitle = "On the Universal Creator, Oneness of Existence, and Compassion",
            content = """
اول الله عليم، اعليٰ عالم جو ڌڻي؛
قادر پنهنجي قدرت سين، قائم آهي قديم؛
والي، واحد، وڏو، رازق، رب رحيم؛
سو ساراه سچو ڌڻي، جنهن جو حمد حڪيم؛
ڪري پاڻ ڪريم، جوڙون جوڙ جهان جي.

وحدت تان ڪثرت ٿي، ڪثرت وحدت ڪل،
حق حقيقي هيڪڙو، ٻولي ٻي م ڀُل؛
هو هلئو هو هل، سو ڌڻي سڀني ۾.

پاتو نينهن نياز سين، جن سڄڻ کي سار،
تن تن اندر تار، ٻيو سڀ وسريو.

[English Translation & Reflection]
In the beginning is Allah, All-Knowing, Supreme Lord of the Cosmos;
By His innate power, Eternal and Changeless from eternity;
The Protector, One, Magnificent, Nourisher, and Merciful Lord;
Praise that True Sovereign, Whose wisdom governs every atom;
He fashioned the wondrous tapestry of existence with boundless grace.

From Unity arose Multiplicity, and Multiplicity converges into Unity;
The Ultimate Reality is One—let no tongue lead you into duality;
That Divine Presence resounds in every song, and abides within every heart.

Shah Abdul Latif Bhittai begins the Risalo not with dogma, but with an ecstatic hymn to Universal Peace (Kalyan means peace, well-being, and salvation). The poet establishes that all humanity belongs to one divine origin, and love is the solitary bridge that reconciles outward differences.
            """.trimIndent(),
            estimatedMinutes = 6
        ),
        ChapterEntity(
            id = "shah-jo-risalo_ch2",
            bookId = "shah-jo-risalo",
            chapterIndex = 1,
            title = "Sur Yaman Kalyan - Dastan 2",
            titleNative = "سر يمن ڪلياڻ - داستان ٻيو",
            subtitle = "The Healer, The Affliction, and the Path of Sincerity",
            content = """
تون حبيب، تون طبيب، تون ئي دردن جي دوا؛
جانب، منهنجي جيءَ ۾، آزارين گهڻا؛
ڏيکاريندين سڄڻ، ڪڏهن مونکي شفا؛
پوءِ به پڇڻ پنهنجي کي، آهين تون ئي سزا.

طبيبين تنوار، وڃي ڪيو ويرن سان؛
ڏک ڏيئي ڏکيءَ کي، پڇن ٿا پرمار؛
سو ڪيئن ٿئي نروار، جنهن جو سور اندر ۾.

ڪُٺيس ڪانَ ڪمان مان، ڇٽي جنهن جو تير؛
سڄڻ سڄو ٿيو، ويو درد ڇڏي دروير.

[English Translation & Reflection]
Thou art the Beloved, Thou art the Physician, Thou art the remedy for every wound;
Beloved, deep within my soul remain burdens manifold;
When wilt Thou grant solace and cure to this longing pilgrim?
Yet even in trials, Thy remembrance is sweet reward.

The healers prescribe potions for earthly fevers, yet they know naught of the heart's yearning;
The true wound of longing can only be healed by the hand that bestowed it.
Shah Latif transforms personal struggle into spiritual alchemy: suffering is not punishment, but the fire in which pride dissolves, leaving gold in the crucible.
            """.trimIndent(),
            estimatedMinutes = 5
        ),
        ChapterEntity(
            id = "shah-jo-risalo_ch3",
            bookId = "shah-jo-risalo",
            chapterIndex = 2,
            title = "Sur Sasui Abri - Dastan 1",
            titleNative = "سر سسئي آبري - داستان پهريون",
            subtitle = "The Desert Quest: Courage in the Face of Mountains",
            content = """
ڏونگر ڏکوئيندءِ، متان هلڻ ڇڏين هٿان؛
پير ٻڌي پر پير سين، وڌائج وکون وڌ؛
پنڌ پريان جو پڌرو، نڪا حد نڪو ڪٿ؛
مر مرڻاڳي مت، سڄڻ ملندءِ سامهون.

ڏکيون ڏونگر لڪيون، سنها پير سسئي؛
پٿرن پير چور ڪيا، رت ڳاڙي رتي؛
تن تپي، هڏ ٻري، اکين نير وسي؛
پرين پنهون لئي، لڪين لنگهي پار ٿي.

[Reflections on Sasui's Pilgrimage]
The jagged mountains of Pubb and the barren passes will try to break thy resolve;
Beware! Do not slacken thy pace nor surrender the quest!
Bind endurance upon thy feet, and lengthen thy stride into the unknown;
The road to the Beloved is boundless, without marker or measure;
Let courage reign supreme: the seeker shall meet the Truth face to face.

Sasui embodies the archetype of fearless human determination. Abandoned in the desert, she refuses to lament in passive helplessness. She marches into the scorching hills of Makran, demonstrating that the spiritual struggle (Jihad al-Nafs) is an active, persevering journey towards the beloved ideal.
            """.trimIndent(),
            estimatedMinutes = 6
        ),
        ChapterEntity(
            id = "shah-jo-risalo_ch4",
            bookId = "shah-jo-risalo",
            chapterIndex = 3,
            title = "Sur Marui - Dastan 1",
            titleNative = "سر مارئي - داستان پهريون",
            subtitle = "Freedom, Patriotism, and the Voice of Malir",
            content = """
جي هاڻي هتي مري وڃان، ته نيئي منهنجو مڙهه ملير ڏجو؛
ٿڌي واري ٿر جي، متان ڇانو ڇڏي ڏجو؛
کائيندا کٿيريون، جتي مارو مون جھڙا؛
تن منجهان مونکي، پرينءَ پير ڏجو.

محلن ۾ مارئيءَ کي، ڪو نه وڻي وڳو؛
سون رپو عمر جو، لڳي پٿر جهڙو؛
پٽ پيهي جنهن پهريو، لوئي لاهي نه اڳو؛
ماروءَ سندو سڳو، سو ئي زيور زينت مون.

[English Translation]
If I should breathe my last in this gilded palace, carry my mortal remains back to Malir;
Lay me beneath the cool sands of the Thar desert, where the wild desert lilies bloom;
Where my humble kinfolk in coarse blankets tend their herds;
Let the earth that nurtured my ancestors embrace my dust.

In Umar's lavish fort, no silk or jewel can tempt Marui. Royal captivity cannot diminish her fidelity to her people, her heritage, and her freedom. Marui remains the timeless emblem of resistance against tyranny and love for the soil.
            """.trimIndent(),
            estimatedMinutes = 7
        ),

        // Diwan-e-Ghalib Chapters
        ChapterEntity(
            id = "diwan-e-ghalib_ch1",
            bookId = "diwan-e-ghalib",
            chapterIndex = 0,
            title = "Ghazliyat: Haqeeqat-e-Hasti",
            titleNative = "غزلیاتِ اول: حقیقتِ ہستی",
            subtitle = "Foundational Ghazals of Existential Wonder",
            content = """
نقش فریادی ہے کس کی شوخیِ تحریر کا
کاغذی ہے پیرہن ہر پیکرِ تصویر کا

کاو کاوِ سخت جانی ہائے تنہائی نہ پوچھ
صبح کرنا شام کا لانا ہے جوئے شیر کا

جزبۂ بے اختیارِ شوق دیکھا چاہیے
سینہ شمشیر سے باہر ہے دم شمشیر کا

آگہی دامِ شنیدن جس قدر چاہے بچھائے
مدعا عنقا ہے اپنے عالمِ تقریر کا

بس کہ ہوں غالب اسیری میں بھی آتش زیر پا
موئے آتش دیدہ ہے حلقہ مری زنجیر کا

[تشریح و تفہیم]
مرزا غالب کا یہ مطلع اردو شاعری کی تاریخ میں فلسفیانہ گہرائی کی علامت ہے۔ تصویر کا لباس کاغذی ہونا اس بات کا اشارہ ہے کہ انسانی وجود اور کائنات کے مظاہر کس قدر ناپائیدار ہیں۔ انسان زندگی کے مخمصے میں گرفتار ہے اور علم و عقل کے جال (دامِ شنیدن) اکثر حقیقت کی وسعتوں کو مکمل طور پر گرفت میں لینے سے قاصر رہتے ہیں۔
            """.trimIndent(),
            estimatedMinutes = 5
        ),
        ChapterEntity(
            id = "diwan-e-ghalib_ch2",
            bookId = "diwan-e-ghalib",
            chapterIndex = 1,
            title = "Hazaron Khwahishein Aisi",
            titleNative = "ہزاروں خواہشیں ایسی",
            subtitle = "The Symphony of Human Longing and Irony",
            content = """
ہزاروں خواہشیں ایسی کہ ہر خواہش پہ دم نکلے
بہت نکلے مرے ارمان لیکن پھر بھی کم نکلے

ڈرے کیوں میرا قاتل کیا رہے گا اس کی گردن پر
وہ خوں جو چشمِ تر سے عمر بھر یوں دم بدم نکلے

نکلنا خلد سے آدم کا سنتے آئے ہیں لیکن
بہت بے آبرو ہو کر ترے کوچے سے ہم نکلے

محبت میں نہیں ہے فرق جینے اور مرنے کا
اسی کو دیکھ کر جیتے ہیں جس کافر پہ دم نکلے

کہاں میخانے کا دروازہ غالبؔ اور کہاں واعظ
پر اتنا جانتے ہیں کل وہ جاتا تھا کہ ہم نکلے

[Commentary & Meaning]
A masterclass in human psychology and poetic irony. Ghalib captures the insatiable nature of human ambition: each desire consumes a lifetime, yet desire itself remains endlessly reborn. His address to the preacher (Waiz) exposes societal hypocrisy with gentle mockery, championing authentic inner sincerity over outward piety.
            """.trimIndent(),
            estimatedMinutes = 5
        ),
        ChapterEntity(
            id = "diwan-e-ghalib_ch3",
            bookId = "diwan-e-ghalib",
            chapterIndex = 2,
            title = "Bazeecha-e-Atfaal",
            titleNative = "بازیچۂ اطفال ہے دنیا مرے آگے",
            subtitle = "The World as a Child's Play",
            content = """
بازیچۂ اطفال ہے دنیا مرے آگے
ہوتا ہے شب و روز تماشا مرے آگے

اک کھیل ہے اورنگِ سلیماں مرے نزدیک
اک بات ہے اعجازِ مسیحا مرے آگے

جز نام نہیں صورتِ عالم مجھے منظور
جز وہم نہیں ہستیِ اشیا مرے آگے

ہوتا ہے نہاں گرد میں صحرا مرے ہوتے
گھستا ہے جبیں خاک پہ دریا مرے آگے

مت پوچھ کہ کیا حال ہے میرا ترے پیچھے
تو دیکھ کہ کیا رنگ ہے تیرا مرے آگے

سچ کہتے ہو خودبین و خودآرا ہوں نہ کیوں ہوں
بیٹھا ہے بتِ آئنہ سیما مرے آگے

ایماں مجھے روکے ہے جو کھینچے ہے مجھے کفر
کعبہ مرے پیچھے ہے کلیسا مرے آگے

[English Notes]
To Ghalib, the spectacle of mortal majesty is like a child's toy. The thrones of monarchs and the worldly chase for prestige fade when placed beside the vastness of eternity and genuine insight.
            """.trimIndent(),
            estimatedMinutes = 5
        ),

        // Kulliyat-e-Iqbal Chapters
        ChapterEntity(
            id = "kulliyat-e-iqbal_ch1",
            bookId = "kulliyat-e-iqbal",
            chapterIndex = 0,
            title = "The Call of the Caravan (Bang-e-Dra)",
            titleNative = "بانگِ درا: طلوعِ اسلام اور پیام",
            subtitle = "Awakening of the Intellect and Moral Consciousness",
            content = """
دلیلِ صبحِ روشن ہے ستاروں کی تنک تابی
افق سے آفتاب ابھرا، گیا دورِ گراں خوابی

عروقِ مُردۂ مشرق میں خونِ زندگی دوڑا
سمجھ سکتے نہیں اس راز کو سینا و فارابی

مسلماں کو مسلماں کر دیا طوفانِ مغرب نے
تلاطم ہائے دریا ہی سے ہے گوہر کی سیرابی

عطا اس کو ہوا ہے پھر شعورِ کارِ پیغمبری
وہ خود تقدیرِ دوراں ہے نہیں محتاجِ اسبابی

خودی کو کر بلند اتنا کہ ہر تقدیر سے پہلے
خدا بندے سے خود پوچھے بتا تیری رضا کیا ہے

[Translation & Study]
The fading light of the stars heralds the dawn; the sun ascends over the eastern horizon, ending the epoch of deep slumber.
Elevate thy 'Khudi' (Selfhood / Inner dignity) to such noble heights that before decreeing any destiny, the Creator Himself inquires: 'Tell me, what is thy wish?'

Iqbal’s philosophy centers around 'Khudi'—not self-centered arrogance, but self-discovery, spiritual strength, moral integrity, and purposeful action for the welfare of mankind.
            """.trimIndent(),
            estimatedMinutes = 6
        ),
        ChapterEntity(
            id = "kulliyat-e-iqbal_ch2",
            bookId = "kulliyat-e-iqbal",
            chapterIndex = 1,
            title = "Bal-e-Jibril: Saqi Nama & Ghazals",
            titleNative = "بالِ جبریل: ساقی نامہ اور افکار",
            subtitle = "Beyond the Constellations Lies the Infinite Universe",
            content = """
ستاروں سے آگے جہاں اور بھی ہیں
ابھی عشق کے امتحاں اور بھی ہیں

تہی زندگی سے نہیں یہ فضائیں
یہاں سینکڑوں کارواں اور بھی ہیں

قناعت نہ کر عالمِ رنگ و بو پر
چمن اور بھی، آشیاں اور بھی ہیں

اگر کھو گیا اک نشیمن تو کیا غم
مقاماتِ آہ و فغاں اور بھی ہیں

تو شاہیں ہے پرواز ہے کام تیرا
ترے سامنے آسماں اور بھی ہیں

اسی روز و شب میں الجھ کر نہ رہ جا
کہ تیرے زمان و مکاں اور بھی ہیں

[Reflections]
'Thou art an eagle; flight is thy noble craft; ahead of thee spread infinite skies!'
Iqbal calls upon students, thinkers, and citizens never to become complacent in the face of temporary hardship or immediate material achievements. The horizon of knowledge and virtue is endless.
            """.trimIndent(),
            estimatedMinutes = 5
        ),
        ChapterEntity(
            id = "kulliyat-e-iqbal_ch3",
            bookId = "kulliyat-e-iqbal",
            chapterIndex = 2,
            title = "Zarb-e-Kaleem: Knowledge and Education",
            titleNative = "ضربِ کلیم: علم، تعلیم اور خود داری",
            subtitle = "True Enlightenment versus Passive Imitation",
            content = """
عِلم میں دولت بھی ہے، قدرت بھی ہے، لذت بھی ہے
ایک مشکل ہے کہ ہاتھ آتا نہیں اپنا سراغ

خوش تو ہیں ہم بھی جوانوں کی ترقی سے مگر
لبِ خنداں سے نکل جاتی ہے فریاد بھی ساتھ

ہم سمجھتے تھے کہ لائے گی فراغت تعلیم
کیا خبر تھی کہ چلا آئے گا الحاد بھی ساتھ

علم کے دریا سے نکلے، عقل کی کشتی چلے
روح جب بیدار ہو، روشن کرے دل کا چراغ

[Meaning]
Knowledge holds power, wealth, and delight; yet its true challenge is whether it helps human beings discover their own inner character and social responsibility. Education without conscience is incomplete; genuine wisdom enlightens both heart and mind.
            """.trimIndent(),
            estimatedMinutes = 5
        ),

        // History of Sindh Chapters
        ChapterEntity(
            id = "history-of-sindh_ch1",
            bookId = "history-of-sindh",
            chapterIndex = 0,
            title = "Mohenjo-daro & The Indus Valley Civilization",
            titleNative = "موهن جو دڙو ۽ سنڌو تهذيب",
            subtitle = "Urban Planning, Sanitation, and the Bronze Age Metropolis",
            content = """
The Indus Valley Civilization (flourishing c. 2600–1900 BCE) represents one of the earliest urban achievements of humankind. Situated along the fertile floodplains of the mighty River Indus (Sindhu), Mohenjo-daro was not a citadel of warlords or grandiose tombs, but a testament to community organization, civic engineering, and peaceful craftsmanship.

Key Features of the Ancient Metropolis:
1. Gridiron Urban Layout: Streets laid out on strict north-south and east-west axes, lined with standardized fired-brick houses.
2. The Great Bath: A marvel of hydrologic engineering featuring bitumen waterproofing, dressing chambers, and ritual cleansing reservoirs.
3. Covered Drainage Systems: Every household was connected to subterranean sewer mains—a level of municipal hygiene unmatched until the modern era.
4. Standardization of Weights and Measures: Binary and decimal cubic chert weights indicating sophisticated cross-continental trade with Mesopotamia and Oman.
5. Peaceful Egalitarian Society: Unlike Egypt or Mesopotamia, excavations at Mohenjo-daro reveal no imperial palaces, army garrisons, or monumental monuments to warfare. The bronze Dancing Girl and the soapstone Priest-King reflect artistic refinement and dignified civic order.
            """.trimIndent(),
            estimatedMinutes = 7
        ),
        ChapterEntity(
            id = "history-of-sindh_ch2",
            bookId = "history-of-sindh",
            chapterIndex = 1,
            title = "The River Indus & The Cradle of Languages",
            titleNative = "سنڌو درياءُ ۽ ٻوليءَ جو ارتقا",
            subtitle = "From Ancient Prakrit to Classical Sindhi Literature",
            content = """
The River Indus has rightfully been called the lifeblood of Sindh (سنڌوءَ جي سرزمين). Where the waters surged, life flourished; seasonal inundations deposited silt, feeding grain fields, cotton plantations, and mango orchards.

The Sindhi language is an Indo-Aryan language rooted in the ancient Vracada Apabhramsha and Vedic Sanskrit dialects. Its rich phonology features distinctive implosive consonants (ٻ, ڄ, ڳ, ڏ), reflecting its deep indigenous antiquity.

As noted by scholar Dr. U.M. Daudpota, the geographical position of Sindh made it a bustling maritime and overland crossroads. Scholars, sufi saints, traders, and seafaring dhows carried languages, ideas, and manuscripts across the Indian Ocean to the Persian Gulf and Red Sea ports.
            """.trimIndent(),
            estimatedMinutes = 6
        ),
        ChapterEntity(
            id = "history-of-sindh_ch3",
            bookId = "history-of-sindh",
            chapterIndex = 2,
            title = "The Sufi Renaissance & Intellectual Golden Age",
            titleNative = "صوفياڻو دور ۽ ادبي جاڳرتا",
            subtitle = "Bhittai, Sachal, and the Synthesis of Peace",
            content = """
The 17th and 18th centuries marked an extraordinary flowering of culture in Sindh under Kalhora and Talpur eras. In a period often troubled by dynastic rivalries, the great Sufi poets articulated a philosophy of universal humanism that transcended caste, creed, and sectarian borders.

Shah Abdul Latif of Bhit Shah, Sachal Sarmast of Daraza, Rohal Faqir, and Sami created an enduring cultural commons. When Shah Latif sang:
'سائينم سدائين ڪرين مٿي سنڌ سڪار، دوست تون دلدار، عالم سڀ آباد ڪرين'
(O Lord, may Thou forever shower abundance upon Sindh; O Gracious Friend, make the entire world prosperous and free!), he united love of homeland with love for all humanity.
            """.trimIndent(),
            estimatedMinutes = 6
        ),

        // Sachal Sarmast Chapters
        ChapterEntity(
            id = "sachal-sarmast_ch1",
            bookId = "sachal-sarmast",
            chapterIndex = 0,
            title = "Kalam Ashiqan: Song of the Free Soul",
            titleNative = "ڪلام عاشقان: وحدت جو درياهه",
            subtitle = "Transcending Forms to Touch the Essence",
            content = """
آءُ پنهنجا آهيان، ڪو ٻيو ناهيان؛
نڪا ذات، نڪو پاڻ، نڪا رسم، نڪو رهاڻ؛
مان ته سچ سندو نشان آهيان!

ڪڏهن مسجدين ويٺس، ڪڏهن مندر ميرانجهڙو؛
ڪڏهن شاهه بنجي ويهان، ڪڏهن گدا ٿي گهران؛
صورتن سڀني اندر، صورتِ يار ڏسان.

مذهب عشق جو نيارو، نڪا حجت نڪو قيل؛
سچل سڀني سان پيار، سو ئي پاڪ دليل.

[English Notes]
Sachal Sarmast ('The Truth-telling Ecstatic') cast aside outward prejudice. He sang that truth is not the private monopoly of any single clan or lineage; it belongs to whoever approaches creation with an open, loving heart.
            """.trimIndent(),
            estimatedMinutes = 5
        ),
        ChapterEntity(
            id = "sachal-sarmast_ch2",
            bookId = "sachal-sarmast",
            chapterIndex = 1,
            title = "Jhokan Ja Pandhi (The Wanderers)",
            titleNative = "جهوڪن جا پنڌي",
            subtitle = "Sacrifice, Truth, and the Light of Knowledge",
            content = """
سوريءَ چڙهيا سي، جي هئا سچ جا عاشق؛
تن کي موت جو خوف نه آيو، اڏول رهيا هر دم؛
ڪوٽ قلعا ڊهي پيا، پر سچ جا نعرا قائم رهيا.

پڙهو علم عمل سان، نه رڳو لفظن جا ڍير؛
عمل بنا جو علم آهي، سو بار آهي ڳرو؛
ڏسو اندر پنهنجي کي، تنهن مان ڦٽندو نور ڦڙو.

Sachal cautions that academic rote memorization without ethical action is merely an empty burden. Real knowledge illuminates personal conduct and inspires selflessness.
            """.trimIndent(),
            estimatedMinutes = 5
        ),

        // Muqaddimah Chapters
        ChapterEntity(
            id = "muqaddimah_ch1",
            bookId = "muqaddimah",
            chapterIndex = 0,
            title = "On Asabiyyah (Social Solidarity)",
            titleNative = "العصبية: سماجي ٻڌي ۽ رياست جو قيام",
            subtitle = "How Mutual Trust and Common Purpose Build Societies",
            content = """
Ibn Khaldun identifies 'Asabiyyah' (group feeling, social cohesion, and mutual solidarity) as the primary motor of human civilization. In his profound analysis:

1. The Necessity of Social Organization: Man is social by nature (insan madaniyyun bi-t-tab'); individuals cannot independently secure food, shelter, and security without communal division of labor.
2. The Origin of Solidarity: Asabiyyah begins in kinship and mutual reliance in challenging environments where collective survival requires absolute trust.
3. Transition from Nomadic Ruggedness (Badawah) to Urban Luxury (Hadarah): As societies gain stability, urban centers flourish, arts and sciences blossom, yet luxury can gradually erode the very cohesion that gave birth to the commonwealth.
4. Renewal through Collective Responsibility: A society endures only when its institutions maintain justice, rule of law, and fair treatment for everyday laborers and artisans.
            """.trimIndent(),
            estimatedMinutes = 7
        ),
        ChapterEntity(
            id = "muqaddimah_ch2",
            bookId = "muqaddimah",
            chapterIndex = 1,
            title = "Economics, Taxation, and Justice",
            titleNative = "معاشيات، محصول ۽ انصاف جو نظام",
            subtitle = "The Injustice that Causes the Ruin of Civilizations",
            content = """
In Chapter 3 of the Muqaddimah, Ibn Khaldun formulates his famous economic dictum:

'Injustice brings about the ruin of civilization.' When rulers impose crushing arbitrary levies on merchants, farmers, and craftsmen, their incentive to produce, cultivate, and invest is extinguished. When production collapses, public revenue inevitably dwindles.

Conversely, light and predictable taxation accompanied by secure property rights stimulates enterprise, encourages trade, and elevates the prosperity of the entire community. Justice is not merely an ethical virtue—it is the prerequisite for material survival.
            """.trimIndent(),
            estimatedMinutes = 6
        ),
        ChapterEntity(
            id = "muqaddimah_ch3",
            bookId = "muqaddimah",
            chapterIndex = 2,
            title = "The Transmission of Knowledge & Sciences",
            titleNative = "علوم ۽ فنون جي ترقي ۽ ارتقا",
            subtitle = "Why Learning Requires Civic Stability",
            content = """
Sciences, libraries, and philosophical inquiry can only flourish in societies where basic security, food sustenance, and intellectual tolerance are firmly established.

Ibn Khaldun categorizes knowledge into two major branches:
- Philosophical and Rational Sciences (natural sciences, mathematics, astronomy, logic), accessible to all human minds through observation and reason.
- Traditional and Transmitted Sciences (languages, jurisprudence, history), preserved through textual scholarship and cultural memory.

He praises the compilation of public libraries and warns against pedagogical harshness: students flourish under encouragement, dialectic dialogue, and practical application, rather than brute rote memorization.
            """.trimIndent(),
            estimatedMinutes = 6
        ),

        // Public Governance Chapters
        ChapterEntity(
            id = "public-governance_ch1",
            bookId = "public-governance",
            chapterIndex = 0,
            title = "Principles of Public Trust & Institutional Ethics",
            titleNative = "عوامي اعتماد ۽ ادارتي اخلاقيات",
            subtitle = "Serving the Sovereign Citizen",
            content = """
Public administration exists to translate democratic aspirations into tangible public welfare: clean water, literacy, healthcare, equal justice, and equitable economic opportunity.

Key Pillars of Awami Governance:
1. Transparency: Government budgets, tenders, and public audits must be accessible in plain language to every citizen.
2. Accountability: Public servants are stewards of public resources, not masters of the citizenry.
3. Decentralization: Decisions impacting schools, healthcare clinics, and roads must be taken by elected local councils close to the soil.
4. Protection of the Vulnerable: A society is judged not by the opulence of its elite, but by how it shields children, seniors, laborers, and marginalized communities.
            """.trimIndent(),
            estimatedMinutes = 6
        ),
        ChapterEntity(
            id = "public-governance_ch2",
            bookId = "public-governance",
            chapterIndex = 1,
            title = "Community Libraries & The Right to Read",
            titleNative = "عوامي لائبريريون ۽ مطالعي جو حق",
            subtitle = "Knowledge as a Universal Public Utility",
            content = """
In an era of digital revolutions and economic transitions, access to books and knowledge remains a fundamental human right. Public libraries—such as Awami Library (عوامي لائبريري)—serve as democratic equalizers:

- Free Access: Books, historical documents, and educational curricula available without cost or barriers.
- Offline Availability: Empowering rural and remote communities where broadband connectivity is scarce or intermittent.
- Preserving Regional Heritage: Safeguarding Sindhi, Urdu, and regional indigenous literary treasures alongside modern global sciences.
- Lifelong Learning: Providing civil service preparation materials, language courses, and creative reading spaces for youth.
            """.trimIndent(),
            estimatedMinutes = 5
        ),

        // Folk Tales Chapters
        ChapterEntity(
            id = "sindhi-folk-tales_ch1",
            bookId = "sindhi-folk-tales",
            chapterIndex = 0,
            title = "The Tale of Umar & Marvi",
            titleNative = "عمر مارئيءَ جو داستان",
            subtitle = "Integrity, Simplicity, and Devotion to the Motherland",
            content = """
In the desert landscape of Thar, at the well of Bhalwa, lived Marvi, a humble maiden betrothed to her kinsman Khetsen. Hearing of her beauty, King Umar of Umerkot came in disguise and abducted her to his fortified castle.

Umar offered Marvi royal garments of Persian silk, chests of rubies, and status as premier queen of the realm. Marvi steadfastly rejected all imperial enticements. Dressed in her simple homespun woolen shawl ('loi'), she looked toward Malir every dawn and wept for her people.

She declared: 'O King! To thee, palaces and golden vessels are precious; but to me, the thorny shrubs of the Thar desert, the wild berries of the Kandi tree, and the dust of my homeland are dearer than all the riches of the world.' Moved by her incorruptible virtue, Umar ultimately bowed in reverence and escorted Marvi safely back to her clan.
            """.trimIndent(),
            estimatedMinutes = 7
        ),
        ChapterEntity(
            id = "sindhi-folk-tales_ch2",
            bookId = "sindhi-folk-tales",
            chapterIndex = 1,
            title = "Sassui and Punhun: The Trek Through Pubb",
            titleNative = "سسئي پنهون: سچي طلب جو سفر",
            subtitle = "The Perils of the Baloch Hills and Unyielding Love",
            content = """
Punhun, the noble prince of Kech Makran, fell in love with Sassui, the washerman's adopted daughter in Bhambhore on the Arabian Sea coast. Overcoming social barriers, they were wed.

Yet Punhun's brothers drugged the prince at night and carried him away on swift camels back to the mountains. Waking at dawn to an empty chamber, Sassui did not surrender to despair. With bare feet, she set forth across the trackless crags of Pubb and Makran.

When shepherds warned her of wolves, heat, and thirst, she replied that the only true death is to stop seeking. The story has been immortalized by hundreds of poets as the allegory of the soul's relentless journey towards divine enlightenment.
            """.trimIndent(),
            estimatedMinutes = 6
        ),
        ChapterEntity(
            id = "sindhi-folk-tales_ch3",
            bookId = "sindhi-folk-tales",
            chapterIndex = 2,
            title = "Sohni and Mehar: Across the Raging Waves",
            titleNative = "سھڻي ميهار: محبت ۽ درياءُ جو طوفان",
            subtitle = "The Unbaked Earthen Vessel and the Test of Faith",
            content = """
Every night, Sohni crossed the turbulent waters of the River Chenab to meet Mehar, aided by a baked clay pot ('gharra') that kept her afloat against the churning river currents.

One dark stormy night, her jealous sister-in-law substituted the baked pot with an unbaked, brittle clay vessel made of soft river mud. In the midst of the roaring midnight whirlpools, the clay pot began to dissolve in her hands.

Sohni knew that turning back would mean self-preservation at the cost of fidelity. She let the mud slip away and entrusted her body to the waves, swimming toward her beloved until the river took her into its eternal embrace.
            """.trimIndent(),
            estimatedMinutes = 6
        )
    )
}

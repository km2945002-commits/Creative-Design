package com.example.data

data class ServiceItem(
    val id: String,
    val name: String,
    val shortTag: String,
    val category: String,
    val tagline: String,
    val description: String,
    val heroHighlight: String,
    val startingPricePkr: String,
    val startingPriceUsd: String,
    val estimatedTurnaround: String,
    val deliverables: List<String>,
    val techOrMaterials: List<String>,
    val features: List<ServiceFeature>
)

data class ServiceFeature(
    val title: String,
    val description: String
)

data class PortfolioProject(
    val id: String,
    val title: String,
    val client: String,
    val category: String,
    val categoryLabel: String,
    val tagline: String,
    val toolsUsed: String,
    val timeline: String,
    val format: String,
    val finishOrStack: String,
    val deliverables: List<String>,
    val accentColorHex: Long = 0xFFFBBF24
)

data class TechStackItem(
    val name: String,
    val category: String,
    val badge: String,
    val description: String,
    val workflowUse: List<String>
)

data class WebsiteSolution(
    val id: String,
    val title: String,
    val subtitle: String,
    val idealFor: String,
    val description: String,
    val keyFeatures: List<String>,
    val recommendedTech: String
)

data class StudioFaq(
    val question: String,
    val answer: String
)

object StudioData {
    const val STUDIO_NAME = "Creative Design Studio"
    const val STUDIO_LOCATION = "Suhan Islamabad, Pakistan"
    const val STUDIO_PHONE = "+92 319 9774681"
    const val STUDIO_WHATSAPP_NUMBER = "923199774681"
    const val STUDIO_EMAIL = "km2945002@gmail.com"
    const val STUDIO_LEAD_DESIGNER = "Mortaza Khan"
    const val STUDIO_AVAILABILITY = "Taking New Commissions"

    val services = listOf(
        ServiceItem(
            id = "web-apps",
            name = "Websites & Apps Design",
            shortTag = "Web & Mobile",
            category = "web-app",
            tagline = "Modern, responsive websites and app interfaces using AI-powered tools, WordPress, and Hostinger.",
            description = "We design modern, responsive websites and app interfaces using AI-powered design tools, WordPress, and Hostinger deployment. Fast loading, mobile friendly, and optimized for conversions.",
            heroHighlight = "High-speed responsive layouts, WordPress CMS setup, Hostinger cloud deployment, and AI-accelerated UX prototyping.",
            startingPricePkr = "PKR 45,000",
            startingPriceUsd = "$160",
            estimatedTurnaround = "5 to 7 Days",
            deliverables = listOf(
                "Modern responsive website layouts (Desktop, Tablet, Mobile)",
                "WordPress CMS setup and custom page templates",
                "Hostinger domain setup, SSL security & email configuration",
                "Speed optimization, Core Web Vitals & SEO foundations",
                "Contact forms, WhatsApp floating action button & lead captures",
                "Admin training documentation and handover files"
            ),
            techOrMaterials = listOf(
                "WordPress", "Hostinger Cloud", "AI Prototyping", "React & Kotlin", "Core Web Vitals", "SSL & CDN"
            ),
            features = listOf(
                ServiceFeature(
                    title = "Rapid Prototyping & Modern UI",
                    description = "Cutting-edge responsive layouts tailored to your exact brand tone with intuitive user journeys."
                ),
                ServiceFeature(
                    title = "WordPress CMS & Client Handover",
                    description = "Easily update text, imagery, and products without touching code via an intuitive admin dashboard."
                ),
                ServiceFeature(
                    title = "Hostinger Cloud Infrastructure",
                    description = "Seamless deployment on reliable SSD servers with 99.9% uptime and business email setup."
                )
            )
        ),
        ServiceItem(
            id = "logo-design",
            name = "Logo & Vector Design",
            shortTag = "Identity & Vectors",
            category = "logo",
            tagline = "Sharp mathematical vectors in CorelDRAW & Illustrator that never pixelate at any scale.",
            description = "We craft distinctive, memorable logos and brand marks engineered with mathematical vector curves in CorelDRAW and Adobe Illustrator. Designed to look stunning from tiny social avatars to giant billboards.",
            heroHighlight = "Custom geometric monogram and emblem vector paths, optical kerning, and color separation packages.",
            startingPricePkr = "PKR 45,000",
            startingPriceUsd = "$160",
            estimatedTurnaround = "5 to 7 Days",
            deliverables = listOf(
                "Primary, horizontal, and compact icon mark variations",
                "Pantone coated & uncoated spot color calibrations",
                "High-resolution transparent PNG and print PDF/X-1a files",
                "Vector master source files (.CDR, .AI, .SVG, .EPS)",
                "Full color, monochrome, and inverted dark mode versions",
                "Logo usage guidelines and minimum size rules"
            ),
            techOrMaterials = listOf(
                "CorelDRAW 2024", "Adobe Illustrator", "Vector Bezier Curves", "Pantone Standards", "CMYK & RGB Calibration"
            ),
            features = listOf(
                ServiceFeature(
                    title = "CorelDRAW Vector Precision",
                    description = "Sharp mathematical vectors that never pixelate, scalable to any billboard or embroidery."
                ),
                ServiceFeature(
                    title = "Brand-Focused Typography",
                    description = "Pairings of modern fonts, letter-spacing, and hierarchy that convey trust and authority."
                ),
                ServiceFeature(
                    title = "Complete Master Package",
                    description = "Delivered in transparent PNG, vector SVG, print PDF, and editable CorelDRAW / AI files."
                )
            )
        ),
        ServiceItem(
            id = "business-cards",
            name = "Business Card Design",
            shortTag = "Print & Cards",
            category = "business-card",
            tagline = "Premium business cards, corporate stationery, and letterheads ready for professional printing.",
            description = "We design premium business cards, corporate stationery, letterheads, and professional print-ready collateral using CorelDRAW and Adobe Illustrator with exact press bleeds.",
            heroHighlight = "Front & back custom layouts, 3mm press bleeds, gold/silver foil callouts, and master print files.",
            startingPricePkr = "PKR 35,000",
            startingPriceUsd = "$125",
            estimatedTurnaround = "3 to 5 Days",
            deliverables = listOf(
                "Custom business card design with front & back layouts",
                "Print-ready files with standard press bleeds (3mm)",
                "High-resolution 300 DPI CMYK PDF/X print masters",
                "Foil mask separation layer and spot UV callouts",
                "Matching corporate letterhead & envelope layouts",
                "Editable CorelDRAW (.CDR) and Illustrator (.AI) source files"
            ),
            techOrMaterials = listOf(
                "CorelDRAW", "Adobe Illustrator", "3mm Press Bleeds", "300 DPI CMYK", "Spot UV & Foil Masks"
            ),
            features = listOf(
                ServiceFeature(
                    title = "Print-Ready Standardization",
                    description = "Pre-set with 300 DPI resolution, CMYK color profiles, cut-lines, and press bleeds."
                ),
                ServiceFeature(
                    title = "Corporate Identity Consistency",
                    description = "Aligned with your logo, color codes, phone numbers, WhatsApp, and social handles."
                ),
                ServiceFeature(
                    title = "Flexible Source Formats",
                    description = "Provided in editable CorelDRAW (.CDR) or Illustrator (.AI) plus instant print PDFs."
                )
            )
        ),
        ServiceItem(
            id = "products-packaging",
            name = "Products & Packaging Design",
            shortTag = "Packaging & Labels",
            category = "product",
            tagline = "Attractive product labels, packaging layouts, and photorealistic mockups using CorelDRAW & AI.",
            description = "We create attractive product labels, structural packaging, product mockups, and promotional visuals using CorelDRAW, Adobe Illustrator, and AI design tools.",
            heroHighlight = "Custom product packaging dielines, regulatory bottle labels, box layouts, and 3D mockups.",
            startingPricePkr = "PKR 85,000",
            startingPriceUsd = "$300",
            estimatedTurnaround = "7 to 10 Days",
            deliverables = listOf(
                "Production-ready structural dieline with cut and crease guides",
                "Front, back, and side regulatory label layouts",
                "Barcode (UPC/EAN) placement and nutrition/spec panels",
                "3D photorealistic product presentation mockups",
                "Layered CorelDRAW (.CDR) and Illustrator (.AI) source archives",
                "Print-ready high-resolution CMYK master PDFs"
            ),
            techOrMaterials = listOf(
                "CorelDRAW", "Adobe Illustrator", "AI Image Tools", "Dieline Measurements", "Barcode EAN/UPC", "3D Renders"
            ),
            features = listOf(
                ServiceFeature(
                    title = "Shelf & E-Commerce Impact",
                    description = "Visual styling designed to capture consumer attention on physical retail shelves and online catalogs."
                ),
                ServiceFeature(
                    title = "Accurate Packaging Dielines",
                    description = "Precision cut-and-fold guidelines tailored to your box, pouch, bottle, or jar specs."
                ),
                ServiceFeature(
                    title = "Realistic 3D Mockup Presentation",
                    description = "High-definition digital mockups ready for social media advertising and product presentations."
                )
            )
        ),
        ServiceItem(
            id = "flex-banner",
            name = "Flex & Banner Design",
            shortTag = "Large-Format Print",
            category = "banner",
            tagline = "Professional flex banners, promotional posters, and large-format print graphics.",
            description = "We design professional flex banners, advertising banners, posters, promotional materials, and print-ready graphics using CorelDRAW and Adobe Illustrator.",
            heroHighlight = "Outdoor PVC flex banners, trade show roll-ups, shop signboards, and event backdrops.",
            startingPricePkr = "PKR 30,000",
            startingPriceUsd = "$110",
            estimatedTurnaround = "2 to 4 Days",
            deliverables = listOf(
                "Grand-scale print master TIFF & PDF at 150 DPI calibrated scale",
                "Brass eyelet and pole-pocket placement template guide",
                "Digital companion social media banners (1:1 & 16:9)",
                "Outdoor solvent ink color profile alignment",
                "Shopfront fascia & roadside advertising layouts",
                "Editable CorelDRAW source files"
            ),
            techOrMaterials = listOf(
                "CorelDRAW", "Adobe Illustrator", "Large-Format Calibration", "CMYK Solvent Inks", "Grommet Guides"
            ),
            features = listOf(
                ServiceFeature(
                    title = "Long-Distance Readability",
                    description = "High-contrast typography, bold headlines, and vibrant visuals visible from far distances."
                ),
                ServiceFeature(
                    title = "Large-Format Print Specs",
                    description = "Calibrated file resolutions suitable for grand printing on vinyl, canvas, PVC, or backlit flex."
                ),
                ServiceFeature(
                    title = "Promotional Versatility",
                    description = "Designs tailored for shop front fascias, roadside displays, exhibitions, or special events."
                )
            )
        ),
        ServiceItem(
            id = "brand-identity",
            name = "Brand Identity Design",
            shortTag = "Full Brand System",
            category = "brand-identity",
            tagline = "Complete brand systems with logos, color palettes, typography, stationery, and guidelines.",
            description = "We develop complete brand identity systems, including logos, color palettes, typography, business cards, stationery, social media graphics, and brand guidelines.",
            heroHighlight = "End-to-end visual branding ensuring absolute consistency across digital and print touchpoints.",
            startingPricePkr = "PKR 75,000",
            startingPriceUsd = "$270",
            estimatedTurnaround = "1 to 2 Weeks",
            deliverables = listOf(
                "Comprehensive brand identity manual covering all logo usages",
                "Primary, secondary, and sub-mark badge lockups",
                "Color palette guidelines with CMYK, RGB, and HEX codes",
                "Font pairings and typographic hierarchy rules",
                "Business card and letterhead layouts",
                "Social media profile and banner templates",
                "Full suite of vector, print, and digital master files"
            ),
            techOrMaterials = listOf(
                "CorelDRAW", "Adobe Illustrator", "Brand Style Guides", "Design Systems", "Digital & Print Assets"
            ),
            features = listOf(
                ServiceFeature(
                    title = "Unified Brand Cohesion",
                    description = "Ensure every customer touchpoint reflects the same professional tone, color harmony, and quality."
                ),
                ServiceFeature(
                    title = "Clear Usage Guidelines",
                    description = "Practical brand guidelines showing how to use colors, fonts, and logos correctly on any media."
                ),
                ServiceFeature(
                    title = "Social & Print Ready Assets",
                    description = "Ready-to-use templates for Instagram, Facebook, LinkedIn, WhatsApp, and office stationery."
                )
            )
        )
    )

    val projects = listOf(
        PortfolioProject(
            id = "creative-labs-web",
            title = "CreativeLabs Responsive Web & App Platform",
            client = "CreativeLabs Global",
            category = "web-app",
            categoryLabel = "Website & App UI Design",
            tagline = "Modern, responsive website and web application built with AI-assisted layout, WordPress, and Hostinger cloud deployment.",
            toolsUsed = "WordPress · Hostinger · React 19 · AI UI Tools",
            timeline = "4 Weeks",
            format = "Responsive Web Platform & PWA",
            finishOrStack = "WordPress · Hostinger · Mobile Responsive",
            accentColorHex = 0xFF38BDF8,
            deliverables = listOf(
                "Interactive responsive desktop & smartphone layouts",
                "WordPress CMS customization with clean admin dashboard",
                "Hostinger domain setup, SSL security & email configuration",
                "SEO schema markup and 0.72s page transition speeds"
            )
        ),
        PortfolioProject(
            id = "apex-crest-logo",
            title = "Apex Dynamics Geometric Monogram & Logo Mark",
            client = "Apex Industrial Technologies",
            category = "logo",
            categoryLabel = "Logo Design",
            tagline = "Precision vector logo created in CorelDRAW featuring geometric balance, optical kerning, and complete color separations.",
            toolsUsed = "CorelDRAW 2024 · Adobe Illustrator · Vector Bezier Math",
            timeline = "5 Days",
            format = "Vector Bezier Master Package",
            finishOrStack = "CorelDRAW · Adobe Illustrator · SVG / EPS / CDR",
            accentColorHex = 0xFFFBBF24,
            deliverables = listOf(
                "Primary, horizontal, and compact icon mark variations",
                "Pantone coated & uncoated spot color calibrations",
                "High-resolution transparent PNG and print PDF/X-1a files",
                "Editable master CorelDRAW vector files (.CDR / .AI)"
            )
        ),
        PortfolioProject(
            id = "executive-black-cards",
            title = "Executive Corporate Business Cards & Stationery",
            client = "Khyber Strategic Capital",
            category = "business-card",
            categoryLabel = "Business Card Design",
            tagline = "Ultra-clean double-sided executive cards with 3mm press bleeds, gold foil callouts, and CorelDRAW print layouts.",
            toolsUsed = "CorelDRAW · Adobe Illustrator · CMYK Press Bleeds",
            timeline = "3 Days",
            format = "Standard 3.5\" x 2\" with 3mm Bleeds",
            finishOrStack = "300 DPI CMYK · CorelDRAW Master",
            accentColorHex = 0xFFEC4899,
            deliverables = listOf(
                "Double-sided print-ready PDF/X files with crop & bleed marks",
                "Foil mask separation layer and matte finish callouts",
                "High-resolution JPG/PNG proofs for client review",
                "Full typography and vector logo layout source files"
            )
        ),
        PortfolioProject(
            id = "lumina-botanical-packaging",
            title = "Lumina Herbal Skincare Bottle & Box Packaging",
            client = "Lumina Wellness Labs",
            category = "product",
            categoryLabel = "Product & Packaging Design",
            tagline = "Structural folding carton dielines, luxury bottle labels, and 3D product mockup visuals.",
            toolsUsed = "CorelDRAW · Adobe Illustrator · 3D Mockup Tools",
            timeline = "8 Days",
            format = "Custom Dieline + 3D Product Mockups",
            finishOrStack = "Soft-Touch Matte · Spot Foil · Barcode Tested",
            accentColorHex = 0xFF10B981,
            deliverables = listOf(
                "Production-ready structural dieline with cut and crease guides",
                "Front, back, and side regulatory label layouts",
                "3D photorealistic product presentation mockups",
                "Layered CorelDRAW (.CDR) and Illustrator (.AI) source archives"
            )
        ),
        PortfolioProject(
            id = "grand-highway-flex",
            title = "Metro Expressway Grand-Scale Outdoor Flex Banner",
            client = "Capital Urban Real Estate",
            category = "banner",
            categoryLabel = "Flex & Banner Design",
            tagline = "High-contrast outdoor PVC vinyl advertising banner engineered in CorelDRAW for maximum long-distance impact.",
            toolsUsed = "CorelDRAW · Large-Format DPI Calibration · Solvent Inks",
            timeline = "3 Days",
            format = "20ft x 10ft Outdoor Frontlit Flex",
            finishOrStack = "UV Solvent Inks · Weatherproof Hemming",
            accentColorHex = 0xFFF97316,
            deliverables = listOf(
                "Grand-scale print master TIFF & PDF at 150 DPI calibrated scale",
                "Brass eyelet and pole-pocket placement template guide",
                "Digital companion social media banners (1:1 & 16:9)",
                "Editable CorelDRAW source files"
            )
        ),
        PortfolioProject(
            id = "vanguard-brand-system",
            title = "Vanguard Complete Corporate Brand Identity System",
            client = "Vanguard Logistics & Fleet",
            category = "brand-identity",
            categoryLabel = "Brand Identity",
            tagline = "Full-spectrum visual identity system unifying logos, color codes, typography, letterheads, social assets, and brand rules.",
            toolsUsed = "CorelDRAW · Adobe Illustrator · Brand Guidelines Manual",
            timeline = "10 Days",
            format = "Complete Brand Identity Manual & Asset Kit",
            finishOrStack = "Vector Suite + Brand Book PDF",
            accentColorHex = 0xFFA855F7,
            deliverables = listOf(
                "30-page brand guidelines manual covering all logo usages",
                "Primary, secondary, and badge logo lockups",
                "CMYK, RGB, and HEX color code standards",
                "Corporate letterhead, envelope, and email signature templates"
            )
        )
    )

    val techStack = listOf(
        TechStackItem(
            name = "CorelDRAW 2024",
            category = "Precision Vector & Print Design",
            badge = "Print Production",
            description = "Professional vector graphics, logos, business cards, flex banners, and large-format print design.",
            workflowUse = listOf(
                "Scalable vector logos with mathematical precision",
                "Print-ready business cards with 3mm bleed margins",
                "Grand-scale outdoor flex banners & event signboards"
            )
        ),
        TechStackItem(
            name = "Adobe Illustrator",
            category = "Vector Graphics & Illustration",
            badge = "Brand Standard",
            description = "Vector graphics, branding, typography systems, and professional digital illustrations.",
            workflowUse = listOf(
                "Complete brand identity manuals & typography sheets",
                "Packaging dielines and custom product labels",
                "Resolution-independent SVG, EPS, and PDF exports"
            )
        ),
        TechStackItem(
            name = "WordPress CMS",
            category = "Content Management & E-Commerce",
            badge = "CMS Platform",
            description = "Professional business websites, blogs, and e-commerce stores with intuitive content management for clients.",
            workflowUse = listOf(
                "Custom business & corporate websites",
                "E-commerce & online store development",
                "Client-friendly content editing & blog setup"
            )
        ),
        TechStackItem(
            name = "Hostinger Cloud",
            category = "Cloud Hosting & Domain Setup",
            badge = "Infrastructure",
            description = "Domain registration, web hosting, website deployment, and business email setup.",
            workflowUse = listOf(
                "Custom domain registration & DNS linkage",
                "High-speed SSD cloud server deployment",
                "Free SSL encryption & professional business email"
            )
        ),
        TechStackItem(
            name = "AI Website Builders",
            category = "Web Design & Prototyping",
            badge = "AI Powered",
            description = "AI-assisted website creation and design that rapidly accelerates layout generation, wireframing, and interactive design.",
            workflowUse = listOf(
                "Rapid UI layout generation & wireframing",
                "Intelligent color palette & section exploration",
                "Mobile-friendly responsive baseline scaffolding"
            )
        ),
        TechStackItem(
            name = "AI Image & Content Tools",
            category = "Creative Visual Exploration",
            badge = "AI Creative",
            description = "Creative concepts, visual assets, website content, and marketing materials to bring ideas to life quickly.",
            workflowUse = listOf(
                "High-resolution conceptual product visuals",
                "Contextual stock imagery & promotional backdrops",
                "Marketing headlines & tailored website copy"
            )
        ),
        TechStackItem(
            name = "Modern Code Engineering",
            category = "Development & Performance",
            badge = "Engineering",
            description = "Website development, clean code architecture, sub-second page transitions, and performance optimization.",
            workflowUse = listOf(
                "Modern React, TypeScript, and Android native execution",
                "Code debugging & speed optimization (Core Web Vitals)",
                "SEO schema markup & accessibility compliance checks"
            )
        )
    )

    val websiteSolutions = listOf(
        WebsiteSolution(
            id = "corporate",
            title = "Business & Corporate Websites",
            subtitle = "Authority, Trust & Lead Generation",
            idealFor = "Consultancies, law firms, real estate, B2B agencies, and corporate enterprises.",
            description = "Polished, authoritative digital platforms designed for consultancies, corporate enterprises, service providers, and agencies looking to win client trust.",
            keyFeatures = listOf(
                "Executive multi-page structure & team profiles",
                "Interactive service catalog with request-quote forms",
                "Client testimonials, case studies & trust badges",
                "Hostinger SSD deployment with corporate email setup",
                "Search-optimized layout and fast loading speed"
            ),
            recommendedTech = "WordPress + Hostinger Cloud + Custom CSS"
        ),
        WebsiteSolution(
            id = "ecommerce",
            title = "E-Commerce & Online Stores",
            subtitle = "Conversion-Driven Shopping Experiences",
            idealFor = "Retailers, fashion labels, wellness brands, electronic stores, and merchants.",
            description = "Full-featured shopping platforms with seamless product catalogs, cart mechanics, mobile checkout, and payment gateway support.",
            keyFeatures = listOf(
                "Product variation matrix (colors, sizes, bundles)",
                "Secure checkout with local and international gateways",
                "Inventory & order management dashboard",
                "Direct WhatsApp order integration for instant sales",
                "Automated order confirmation email triggers"
            ),
            recommendedTech = "WooCommerce / Shopify / Next.js Storefront"
        ),
        WebsiteSolution(
            id = "portfolio",
            title = "Creative & Portfolio Showcases",
            subtitle = "Immersive Visual Narratives",
            idealFor = "Photographers, interior designers, architects, consultants, developers, and studios.",
            description = "Visual-first websites crafted to present high-resolution case studies, creative assets, and client accolades with smooth interactions.",
            keyFeatures = listOf(
                "Interactive visual project galleries & lightbox viewports",
                "Client testimonials & credential badges",
                "Downloadable CV / PDF presentation decks",
                "Direct booking link / commission inquiry form",
                "Sub-second page navigation and responsive imagery"
            ),
            recommendedTech = "Modern Web Platform + Cloud CDN"
        ),
        WebsiteSolution(
            id = "brand",
            title = "Brand & Product Landing Pages",
            subtitle = "High-Impact Product Launchpads",
            idealFor = "New product launches, app downloads, events, campaigns, and promotions.",
            description = "High-conversion single-page experiences focused on driving user actions, app installs, or product pre-orders.",
            keyFeatures = listOf(
                "Compelling hero sections with 3D product renders",
                "Sticky call-to-action triggers & conversion funnels",
                "Interactive feature comparisons & countdown timers",
                "Direct WhatsApp lead capture and form validations"
            ),
            recommendedTech = "AI Rapid Scaffolding + Custom React/WordPress"
        )
    )

    val budgetOptions = listOf(
        "PKR 25,000 - 50,000",
        "PKR 50,000 - 100,000",
        "PKR 100,000 - 250,000",
        "PKR 250,000+"
    )

    val faqs = listOf(
        StudioFaq(
            question = "What makes Creative Design Studio unique?",
            answer = "We combine modern AI acceleration with industry-grade software (CorelDRAW, Adobe Illustrator, WordPress, and Hostinger). While AI speeds up conceptual exploration, 100% of the deliverable output is mathematically refined, color-calibrated in CMYK/RGB, and print-tested by human professionals."
        ),
        StudioFaq(
            question = "Do I get editable master source files?",
            answer = "Yes! Depending on your chosen service, you receive editable CorelDRAW (.CDR), Adobe Illustrator (.AI), vector SVG, high-res 300 DPI print PDFs, and transparent PNGs. For websites, you get full admin access to WordPress and Hostinger credentials."
        ),
        StudioFaq(
            question = "How do we get started and how do payments work?",
            answer = "Submit a quote request here in the app or message us directly on WhatsApp (+92 319 9774681). We provide an itemized proposal with deliverables and milestones. Once approved, we begin the discovery and design phase."
        ),
        StudioFaq(
            question = "Can you handle physical printing for banners and cards?",
            answer = "We provide 100% press-ready files with standard 3mm bleeds, cut-lines, and grommet spacing calibrated for local printing presses in Pakistan and worldwide. We can also coordinate with your local print vendor."
        ),
        StudioFaq(
            question = "What is the typical turnaround time?",
            answer = "Banners and cards take 2-5 days. Logos and full brand identities take 5-10 days. Comprehensive websites take 1-4 weeks depending on scope and feature complexity."
        )
    )
}

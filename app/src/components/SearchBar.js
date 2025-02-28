import React, { useState } from "react"
import './SearchBar.css'

export const SearchBar = ({ setResults, setSearchQuery, setShowResultText }) => {
    const [query, setQuery] = useState("");

    const handleChange = (event) => {
        setQuery(event.target.value);
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        // handle search logic here
        setResults([{
            "card": { "name": "Unlicensed Hearse" },
            "source": "HARERUYA",
            "lang": "en",
            "setCode": "OTP",
            "cardNumber": "0064",
            "isFoil": true,
            "listingUrl": "https://scryfall.com/card/otp/64/unlicensed-hearse",
            "price": 56858,
            "condition": "NM",
            "cardImageUrl": "https://cards.scryfall.io/large/front/5/4/5447a777-769d-4773-ab64-31c67e310e15.jpg"
        }, {
            "card": { "name": "The One Ring" },
            "source": "HARERUYA",
            "lang": "en",
            "setCode": "LTR",
            "cardNumber": "0380",
            "isFoil": false,
            "listingUrl": "https://scryfall.com/card/ltr/380/the-one-ring",
            "price": 23443,
            "condition": "LP",
            "cardImageUrl": "https://cards.scryfall.io/large/front/d/b/db80391f-1643-4b72-a397-d141bb5702ee.jpg"
        }, {
            "card": { "name": "Witness Protection" },
            "source": "SERRA",
            "lang": "jp",
            "setCode": "FDN",
            "cardNumber": "0168",
            "isFoil": true,
            "listingUrl": "https://scryfall.com/card/fdn/168/witness-protection",
            "price": 14232,
            "condition": "HP",
            "cardImageUrl": "https://cards.scryfall.io/large/front/f/2/f231e981-0069-43ce-ac1c-c85ced613e93.jpg"
        }])
        setSearchQuery(query)
        setShowResultText(true)
        // data mocked above because just for demoing UI, proper code to perform request to BE for data is below

        // fetch('mock/search/' + query)
        //   .then(response => response.json())
        //   .then(data => {
        //     setResults(data)
        //     setSearchQuery(query)
        //     setShowResultText(true)
        //   })
        console.log(`Searching for ${query}...`);
    }
    return (
        <form onSubmit={handleSubmit}>
          <input class="card-search-bar" type="text" value={query} onChange={handleChange} />
          <button class="card-search-submit-button" type="submit">Search</button>
        </form>
    )
}

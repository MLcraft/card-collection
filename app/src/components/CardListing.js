import React from "react";
import './CardListing.css'

const source = {
    HARERUYA: 'Hareruya',
    SERRA: 'Cardshop Serra',
};

export const CardListing = ({ listing }) => {
    return (
        <div class="responsive">

            <div class="listing-image"><span>{listing.foil && "IS A FOIL"} {/* conditional rendering if is a foil */}</span><a href={listing.listingUrl}><img className="listing-card-image"
                                                                         src={listing.cardImageUrl}
                                                                         alt={listing.cardName}/></a></div>
            <div class="listing-text">
                <p>{listing.card.name}<br/>{source[listing.source]}<br/>¥{listing.price}<br/><span>{listing.lang}</span><span>{listing.condition}</span>
                </p>
            </div>
        </div>
    )
}
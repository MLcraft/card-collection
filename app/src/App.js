import React, { useState, useEffect } from 'react'
import './App.css';

import { SearchBar } from "./components/SearchBar";
import {CardListing} from "./components/CardListing";

const App = () => {
  const [showResultText, setShowResultText] = useState(false);
  const [results, setResults] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");
  useEffect(() => {
      document.title = `${searchQuery} - Search Results`
  }, [results]);
  useEffect(() => {
    document.title = 'MTG JP Shop Search';
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <h1>JP Shop Search for Magic: The Gathering</h1>
        <div className="App-intro">
          <SearchBar setResults={setResults} setSearchQuery={setSearchQuery} setShowResultText={setShowResultText}/>
          {showResultText && <h2>List of products for card with name {searchQuery}</h2>}
          {results.map(result =>
            <CardListing listing={result}></CardListing>
          )}
        </div>
      </header>
    </div>
  );
}

export default App;
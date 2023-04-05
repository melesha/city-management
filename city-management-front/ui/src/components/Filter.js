import React from 'react';

const Filter = ({search}) => {
    return (
        <input type="text" id="cityFilter" onKeyUp={(e) => search(e.target.value)} placeholder="Search for city.." />
    );
};

export default Filter;

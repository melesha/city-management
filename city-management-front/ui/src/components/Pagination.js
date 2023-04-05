import React from 'react'

const Pagination = ({pages, currentPage, paginate}) => {
    const pageNumbers = [];

    for(let i = currentPage - 5; i <= currentPage + 5; i++) {
        if (i > 0 && i <= pages) {
            pageNumbers.push(i);
        }
        if (!pageNumbers) {
            pageNumbers.push(1);
            currentPage = 1;
        }
    }

    return (
        <nav>
            <ul className="pagination">
                {currentPage > 5 && (
                    <li key="first_page" className="page-item">
                        <a onClick={() => paginate(0)} href='#/' >
                            &laquo;
                        </a>
                    </li>
                )}
                {pageNumbers.map(number => (
                    <li key={number} className="page-item">
                        <a onClick={() => paginate(number-1)} href='#/' className= {(currentPage === (number-1) ? 'active' : '')} >
                            {number}
                        </a>
                    </li>
                ))}
                {currentPage < (pages - 5) && (
                    <li key="last_page" className="page-item">
                        <a onClick={() => paginate(pages-1)} href='#/' >
                            &raquo;
                        </a>
                    </li>
                )}
            </ul>
        </nav>
    )
}

export default Pagination;

import Form from "react-bootstrap/Form";
import ListGroup from "react-bootstrap/ListGroup";
import ListGroupItem from "react-bootstrap/ListGroupItem";
import {Fragment, useState} from "react";
import {isStringNullOrEmpty} from "./addProject/FormValidator";

function ProjectList({projectsList, handleListOnClick}) {
    const [list, setList] = useState(projectsList);
    const handleOnChange = (event) => {
        const searchValue = event.target.value;
        if (isStringNullOrEmpty(searchValue)) {
            setList(projectsList);
        } else {
            const newList = projectsList.slice().filter(value => value.name.match(searchValue + ".*"));
            setList(newList);
        }
    };

    return (
        <Fragment>
            <Form.Label htmlFor="search">Wyszukaj projekt po nazwie:</Form.Label>
            <Form.Control type="text" id="search" placeholder="Nazwa projektu" onChange={(e) => handleOnChange(e)}/>
            <ListGroup variant="flush" className="projects-list">
                {list.map(item =>
                    <ListGroupItem key={item.name}
                                   action
                                   variant="light"
                                   onClick={() => handleListOnClick(item)}>
                        {item.name}
                    </ListGroupItem>)}
            </ListGroup>
        </Fragment>
    );
}

export default ProjectList;
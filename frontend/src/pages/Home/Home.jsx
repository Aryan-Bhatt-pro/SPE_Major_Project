//styles
import { Container, Box, LeftBox, RightBox } from "./Home.styles";
import { ButtonFill } from "../../styles/styles";

import images from "../../assets";
import { useState } from "react";

import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";

export const setAuthToken = (token) =>{
  return {
    type: 'SET_AUTH_TOKEN',
    payload: token,
  }
}

const Home = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const [tokenValue, setTokenValue] = useState("");
  
  const handleTextBoxChange = (event) => {
    setTokenValue(event.target.value);
    // console.log(tokenValue);
  };

  const handleSaveToken = () => {
    dispatch(setAuthToken(tokenValue));
  }
  return (
    <Container>
      <div className="home__header">
        <div className="home__logo">
          {/* <img src={images.colorLogo} alt="" /> */}
          <span>Many Notes</span>
        </div>
      </div>
      <Box>
        <LeftBox>
          <div className="home__main-heading">
            One Stop Solution for all your Notes
          </div>
          <p className="home__sub-heading">
            Many Notes helps you to create, organise and save your notes
            effortlessly across all platforms.
          </p>
          <ButtonFill
            className="home__btn"
            onClick={() => navigate("/notes", { state: "notes" })}
          >
            <span>Get Started</span>
          </ButtonFill>
          <input
        type="text"
        value={tokenValue}  // Controlled component: value is controlled by the state
        onChange={handleTextBoxChange}
      />
      <button onClick={handleSaveToken}>SAVE TOKEN</button>
            
        </LeftBox>
        <RightBox>
          <img src={images.homePageGif} alt="home_gif" />
        </RightBox>
      </Box>
    </Container>
  );
};

export default Home;

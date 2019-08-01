import React from 'react';
import logo from './logo.svg';
import './App.css';

const FPS = 30;

const STATE = {
  TOP_LEFT: 'TOP_LEFT',
  TOP_RIGHT: 'TOP_RIGHT',
  BOTTOM_LEFT: 'BOTTOM_LEFT',
  BOTTOM_RIGHT: 'BOTTOM_RIGHT',
}

class App extends React.Component {
  videoRef = React.createRef();

  state = {
    frameData: [],
    currentState: STATE.TOP_LEFT,
    currentFrame: {},
    done: false,
  };

  render() {
    const video = this.videoRef.current;
    if (!this.state.done) {
      return <div className="hoi">
        <video onClick={this.handleFrameClick} src="/movie.mp4" muted ref={this.videoRef} type="video/mp4" >
        </video>
        {this.renderDots()}
        <p>{this.state.currentState}</p>
        <div>
          {video && Math.floor(video.currentTime / video.duration * 100)}
          {!this.isFirstFrame() && <button onClick={this.skipFrame}>Skip frame</button>}
          <button onClick={this.showNextFrame}>Next frame</button>
        </div>
      </div>;
    } else {
      return <pre>{JSON.stringify(this.state.frameData)}</pre>
    }
  }

  renderDots() {
    console.log(this.state.currentFrame)
    return Object.keys(this.state.currentFrame).map(coord => {
      const {x, y} = this.state.currentFrame[coord];
      return <div style={{position: 'absolute', top: y, left: x, width: '5px', height: '5px', backgroundColor: 'red'}}></div>
    })
  }

  handleFrameClick = (event) => {
    const coords = this.videoRef.current.getBoundingClientRect();
    const x = event.clientX - coords.left;
    const y = event.clientY - coords.top;
    
    const {currentFrame, currentState} = this.state;

    let newCurrentFrame;
    if (currentState !== STATE.TOP_LEFT) {
      newCurrentFrame = {
        ...currentFrame,
        [currentState]: {x, y},
      };
    } else {
      newCurrentFrame = {
        [currentState]: {x, y},
      };
    }

    this.setState({
      currentFrame: newCurrentFrame,
      currentState: this.getNextState(currentState),
    });
  }

  getNextState(currentState) {
    switch (currentState) {
      case STATE.TOP_LEFT: return STATE.TOP_RIGHT; 
      case STATE.TOP_RIGHT: return STATE.BOTTOM_LEFT; 
      case STATE.BOTTOM_LEFT: return STATE.BOTTOM_RIGHT; 
      case STATE.BOTTOM_RIGHT: return STATE.TOP_LEFT; 
    }
  }

  skipFrame = () => {
    const video = this.videoRef.current;
    video.currentTime += 1 / FPS;

    const {frameData} = this.state;

    this.setState({
      frameData: [
        ...frameData,
        null,
      ]
    });

    if (video.ended) {
      this.handleDone();
    }
  }

  isFirstFrame() {
    return this.state.frameData.length === 0;
  }

  showNextFrame = () => {
    const video = this.videoRef.current;
    video.currentTime += 1 / FPS;

    console.log(video.currentTime / video.duration * 100);

    const {currentFrame, frameData} = this.state;

    this.setState({
      frameData: [
        ...frameData,
        currentFrame,
      ],
    });

    if (video.ended) {
      this.handleDone();
    }
  }

  handleDone = () => {
    console.log(this.state.frameData)
    this.setState({
      done: true,
    });

    const {frameData} = this.state;

    for (let i = 0; i < frameData.length; i++) {
      const frame = frameData[i];
      if (!frame) {
        const nextFrameIndex = this.findNextFrame(i);
        frameData[i] = this.interpolateFrame(frameData[i - 1], frameData[nextFrameIndex], nextFrameIndex - i);
      }
    }

    this.setState({
      frameData,
    })
  }

  interpolateFrame(previousFrame, nextFrame, stepsBetween) {
    console.log('itnerpolate', previousFrame, nextFrame, stepsBetween)
    return Object.keys(STATE).reduce((acc, val) => {
      console.log(val);
      return {
        ...acc,
        [val]: {
          x: Math.round(previousFrame[val].x + ((nextFrame[val].x - previousFrame[val].x) / (stepsBetween + 1))),
          y: Math.round(previousFrame[val].y + ((nextFrame[val].y - previousFrame[val].y) / (stepsBetween + 1))),
        }
      }
    }, {});
  }

  findNextFrame(index) {
    const {frameData} = this.state;
    for (let i = index + 1; i < frameData.length; i++) {
      if (frameData[i]) {
        return i;
      }
    }

    console.log(JSON.stringify(frameData));
    throw new Error('mag niet');
  }
}

export default App;


type CarProps = {
    setRear : ()=>void,
    updateCar : ()=>void,
    seatOutput : ()=>void,
}

export function CarBackseat(props){
    let seatPosition;
    if(props.index===0){
        seatPosition = "left";
    }else if(props.index===1){
        seatPosition = "middle";
    }else if(props.index===2){
        seatPosition = "right";
    }

    const returnContent = props.seatOutput(props.seatData,"rear");

    return (
        <div className={"rider "+seatPosition}>
            {returnContent}
        </div>
    )
}
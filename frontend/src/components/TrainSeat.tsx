import axios from "axios";

type tournamentFunction = {
    loadTrain : ()=>void,
}

export function TrainSeat(props){
    const seatData = props.seat;
    let unseat;
    if(seatData.userId === props.user.id){
        unseat = <a onClick={()=>unseatTrain()}>
            <img src={seatData.avatar_url} alt="No image" />
        </a>;
    }else{
        unseat = <img src={seatData.avatar_url} alt="No image" />;
    }

    function unseatTrain(){
        axios.delete("/api/train/leaveTrain", {
            data : seatData,
            headers: {
                "Content-Type" : "application/json",
            }
        }).then(()=>{
            props.loadTrain(seatData.tournamentId);
        });

    }
    return (
        <div className={"trainSeat"}>
            {unseat}
        </div>
    );
}
import styles from './TrackListComponent.module.css';

export default function TrackListComponent({tracks, albumArt}){

    return (
    <div className={styles.tracks}>
        <img src={albumArt} alt="album artwork" />
        {tracks.map((track) => (
            <div key={track.id}>
                <ul>
                    <li>{track.name}</li>
                </ul>
            </div>
        ))}
    </div>
    )

}
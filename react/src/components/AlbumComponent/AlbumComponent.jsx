import styles from './AlbumComponent.module.css'

export default function AlbumComponent({ albums}) {

    return (
        <div className={styles.album}>
            {albums.map((album) => (
                <div className={styles.albumCard}>
                    <p key={album.id}>{album.name}</p>
                    <p>{album.releaseDate}</p>
                    <img className={styles.albumImage} src={album.images[1].url} alt={album.name} />
                </div>
            ))}
        </div>
    )
}
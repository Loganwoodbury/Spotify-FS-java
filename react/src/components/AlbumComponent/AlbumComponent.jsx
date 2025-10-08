import styles from './AlbumComponent.module.css'

export default function AlbumComponent({ albums, handleAlbumClick}) {

    return (
        <div className={styles.album}>
            {albums.map((album) => (
                <div className={styles.albumCard} key={album.id}>
                    <p >{album.name}</p>
                    <p>{album.releaseDate}</p>
                    <img className={styles.albumImage} src={album.images[1].url} alt={album.name} onClick={() => handleAlbumClick(album.id, album.images[1].url)}/>
                </div>
            ))}
        </div>
    )
}
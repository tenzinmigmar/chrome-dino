import pygame
from utils import Dino, clouds, ground

pygame.init()

screen = pygame.display.set_mode((1100, 600))
FPS = 10

pygame.display.set_caption("Chrome Dino")
game_icon = pygame.image.load("assets/Dino/dino-icon.png")
pygame.display.set_icon(game_icon)

cloud = pygame.image.load('assets/Other/Cloud.png')
floor = pygame.image.load('Assets/Other/Track.png')

def main():
    sprites = pygame.sprite.Group()
    dino = Dino(50, 400)
    sprites.add(dino)
    clock = pygame.time.Clock()
    cloud1 = 500
    cloud2 = 1200
    x = 0
    x1 = 1202

    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        # tracking ground movement
        if (x + 1202) <= 0:
            x = 1202
        if (x1 + 1202) <= 0:
            x1 = 1202

        # tracking cloud movement
        if cloud1 <= 0:
            cloud1 = 1202
        if cloud2 <= 0:
            cloud2 = 1202

        screen.fill((255, 255, 255))
        sprites.update()
        sprites.draw(screen)
        x -= 20
        x1 -= 20
        ground(floor, screen, x, x1)
        cloud1 -= 20
        cloud2 -= 20
        clouds(cloud, screen, cloud1, cloud2)
        pygame.display.update()
        clock.tick(FPS)

if __name__ == "__main__":
    main()

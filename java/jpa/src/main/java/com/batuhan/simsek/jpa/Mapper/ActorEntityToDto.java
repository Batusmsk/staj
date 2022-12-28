package com.batuhan.simsek.jpa.Mapper;

import java.util.ArrayList;
import java.util.List;

import com.batuhan.simsek.jpa.dto.ActorDto;
import com.batuhan.simsek.jpa.dto.MovieDto;
import com.batuhan.simsek.jpa.entity.Actor;
import com.batuhan.simsek.jpa.entity.Movie;

public class ActorEntityToDto {

		public ActorDto convert(Actor actor) {
			ActorDto actordto = new ActorDto();
			
			actordto.setName(actor.getName());
			actordto.setLastName(actor.getLastName());

			
			return actordto;
		}

		public List<ActorDto> convert(List<Actor> actors) {
			List<ActorDto> returnList=new ArrayList<>();
			for(Actor actor:actors) {
				returnList.add(convert(actor));
			}
			return returnList;
		}
	}


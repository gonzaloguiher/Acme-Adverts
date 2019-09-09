package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.BillboardFileRepository;
import domain.BillboardFile;

@Component
@Transactional
public class StringToBillboardFileConverter implements Converter<String, BillboardFile> {

	@Autowired
	BillboardFileRepository repository;

	@Override
	public BillboardFile convert(String s) {
		BillboardFile res;
		int id;

		try {
			if (StringUtils.isEmpty(s))
				res = null;
			else {
				id = Integer.valueOf(s);
				res = repository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return res;
	}

}
